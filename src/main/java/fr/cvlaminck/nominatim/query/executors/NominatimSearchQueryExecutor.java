package fr.cvlaminck.nominatim.query.executors;

import fr.cvlaminck.builders.uri.Uri;
import fr.cvlaminck.nominatim.exceptions.NominatimAPIException;
import fr.cvlaminck.nominatim.json.PlaceJsonParser;
import fr.cvlaminck.nominatim.model.Place;
import fr.cvlaminck.nominatim.query.builders.NominatimSearchQueryBuilder;
import fr.cvlaminck.nominatim.query.enums.ResponseFormat;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class NominatimSearchQueryExecutor
    extends AbstractNominatimQueryExecutor<NominatimSearchQueryExecutor, NominatimSearchQueryBuilder, Place> {

    public NominatimSearchQueryExecutor() {
        super(new NominatimSearchQueryBuilder());
    }

    @Override
    protected void initNonModifiableQueryParameters(NominatimSearchQueryBuilder builder) {
        builder.format(ResponseFormat.JSONV2);
        builder.addressDetails(true);
    }

    @Override
    protected List<Place> parseResponses(JSONArray responses) throws NominatimAPIException, JSONException {
        List<Place> places = new ArrayList<>();
        PlaceJsonParser parser = new PlaceJsonParser();
        for (int i = 0; i < responses.length(); i++) {
            places.add(parser.parse(responses.getJSONObject(i)));
        }
        return places;
    }

    public NominatimSearchQueryExecutor query(String query) {
        getQueryBuilder().query(query);
        return this;
    }

    public NominatimSearchQueryExecutor street(String street) {
        getQueryBuilder().street(street);
        return this;
    }

    public NominatimSearchQueryExecutor city(String city) {
        getQueryBuilder().city(city);
        return this;
    }

    public NominatimSearchQueryExecutor county(String county) {
        getQueryBuilder().county(county);
        return this;
    }

    public NominatimSearchQueryExecutor state(String state) {
        getQueryBuilder().state(state);
        return this;
    }

    public NominatimSearchQueryExecutor country(String country) {
        getQueryBuilder().country(country);
        return this;
    }

    public NominatimSearchQueryExecutor postalCode(String postalCode) {
        getQueryBuilder().postalCode(postalCode);
        return this;
    }

    public NominatimSearchQueryExecutor countryCodes(List<String> countryCodes) {
        getQueryBuilder().countryCodes(countryCodes);
        return this;
    }

    public NominatimSearchQueryExecutor viewBox(double left, double top, double right, double bottom) {
        getQueryBuilder().viewBox(left, top, right, bottom);
        return this;
    }

    public NominatimSearchQueryExecutor bounded(boolean bounded) {
        getQueryBuilder().bounded(bounded);
        return this;
    }

    public NominatimSearchQueryExecutor excludePlaceIds(List<Integer> placeIds) {
        getQueryBuilder().excludePlaceIds(placeIds);
        return this;
    }

    public NominatimSearchQueryExecutor limit(int limit) {
        getQueryBuilder().limit(limit);
        return this;
    }

    public NominatimSearchQueryExecutor dedupe(boolean dedupe) {
        getQueryBuilder().dedupe(dedupe);
        return this;
    }

    public static void main(String[] args) throws IOException, NominatimAPIException {
        NominatimSearchQueryExecutor executor = new NominatimSearchQueryExecutor();
        List<Place> places = executor.query("Lille")
                .get();
        for (Place p : places) {
            System.out.println(p);
        }
    }
}
