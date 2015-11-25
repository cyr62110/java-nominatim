package fr.cvlaminck.nominatim.query.executors;

import fr.cvlaminck.nominatim.exceptions.NominatimAPIException;
import fr.cvlaminck.nominatim.exceptions.NominatimAPIHttpException;
import fr.cvlaminck.nominatim.exceptions.NominatimAPIResponseException;
import fr.cvlaminck.nominatim.query.builders.AbstractNominatimQueryBuilder;
import org.apache.commons.io.IOUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.Proxy;
import java.net.URL;
import java.util.List;
import java.util.concurrent.*;

public abstract class AbstractNominatimQueryExecutor
        <T extends AbstractNominatimQueryExecutor, QueryBuilder extends AbstractNominatimQueryBuilder, Result> {

    private QueryBuilder queryBuilder = null;

    private ExecutorService executorService = null;

    protected AbstractNominatimQueryExecutor(QueryBuilder queryBuilder) {
        this.queryBuilder = queryBuilder;

        initNonModifiableQueryParameters(queryBuilder);
    }

    protected abstract void initNonModifiableQueryParameters(QueryBuilder builder);

    protected String executeQuery(Proxy proxy) throws IOException, JSONException, NominatimAPIException {
        URL url = queryBuilder.build().toURL();

        System.out.println(url.toString());

        HttpURLConnection urlConnection = null;
        InputStream is = null;
        try {
            if (proxy == null) {
                urlConnection = (HttpURLConnection) url.openConnection();
            } else {
                urlConnection = (HttpURLConnection) url.openConnection(proxy);
            }

            urlConnection.setRequestMethod("GET");
            urlConnection.addRequestProperty("Accept", "application/json");
            urlConnection.addRequestProperty("Accept-Charset", "utf-8");

            int responseCode = urlConnection.getResponseCode();
            if (responseCode == 200) {
                is = urlConnection.getInputStream();
                return IOUtils.toString(is);
            } else {
                throw new NominatimAPIHttpException(responseCode);
            }
        } finally {
            IOUtils.closeQuietly(is);
        }
    }

    protected abstract List<Result> parseResponses(JSONArray responses) throws NominatimAPIException, JSONException;

    public T acceptLanguage(List<String> languages) {
        getQueryBuilder().acceptLanguage(languages);
        return (T) this;
    }

    public T email(String email) {
        getQueryBuilder().email(email);
        return (T) this;
    }

    public Future<List<Result>> getAsync() {
        //If no library-wise executor has been passed to this query executor, we create a single-thread one
        if (executorService == null) {
            executorService = Executors.newSingleThreadExecutor();
        }
        return executorService.submit(new GetResponseCallable());
    }

    public List<Result> get() throws IOException, NominatimAPIException {
        return new GetResponseCallable().call();
    }

    protected QueryBuilder getQueryBuilder() {
        return queryBuilder;
    }

    public void setExecutorService(ExecutorService executorService) {
        this.executorService = executorService;
    }

    private class GetResponseCallable
        implements Callable<List<Result>> {

        @Override
        public List<Result> call() throws IOException, NominatimAPIException {
            String responseBody = executeQuery(null);
            try {
                JSONArray responses = new JSONArray(responseBody);
                return parseResponses(responses);
            } catch (JSONException ex) {
                throw new NominatimAPIResponseException(ex, responseBody);
            }
        }
    }
}
