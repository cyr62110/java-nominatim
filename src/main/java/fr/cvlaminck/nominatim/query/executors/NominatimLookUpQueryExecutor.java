package fr.cvlaminck.nominatim.query.executors;

import fr.cvlaminck.nominatim.exceptions.NominatimAPIException;
import fr.cvlaminck.nominatim.json.PlaceJsonParser;
import fr.cvlaminck.nominatim.model.OsmType;
import fr.cvlaminck.nominatim.model.Place;
import fr.cvlaminck.nominatim.query.builders.AbstractNominatimQueryBuilder;
import fr.cvlaminck.nominatim.query.builders.NominatimLookUpQueryBuilder;
import fr.cvlaminck.nominatim.query.enums.ResponseFormat;
import org.json.JSONArray;
import org.json.JSONException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class NominatimLookUpQueryExecutor
    extends AbstractNominatimQueryExecutor<NominatimLookUpQueryExecutor, NominatimLookUpQueryBuilder, Place> {

    public NominatimLookUpQueryExecutor() {
        super(new NominatimLookUpQueryBuilder());
    }

    @Override
    protected void initNonModifiableQueryParameters(NominatimLookUpQueryBuilder builder) {
        builder.format(ResponseFormat.JSON);
        builder.addressDetails(true);
    }

    @Override
    protected List<Place> parseResponses(JSONArray responses) throws NominatimAPIException, JSONException {
        List<Place> places = new ArrayList<>(responses.length());
        PlaceJsonParser parser = new PlaceJsonParser();
        for (int i = 0; i < responses.length(); i++) {
            places.add(parser.parse(responses.getJSONObject(i)));
        }
        return places;
    }

    public NominatimLookUpQueryExecutor id(OsmType type, long id) {
        getQueryBuilder().id(type, id);
        return this;
    }

    public NominatimLookUpQueryExecutor nameDetails(boolean nameDetails) {
        getQueryBuilder().nameDetails(nameDetails);
        return this;
    }

    public NominatimLookUpQueryExecutor extraTags(boolean extraTags) {
        getQueryBuilder().extraTags(extraTags);
        return this;
    }

    public static void main(String[] args) throws IOException, NominatimAPIException {
        NominatimLookUpQueryExecutor executor = new NominatimLookUpQueryExecutor();
        executor.id(OsmType.NODE, 240109189);
        executor.get();
    }
}
