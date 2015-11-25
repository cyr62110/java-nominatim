package fr.cvlaminck.nominatim.json;

import fr.cvlaminck.nominatim.exceptions.NominatimAPIResponseException;
import fr.cvlaminck.nominatim.model.Box;
import org.json.JSONArray;

public class BoxJsonParser implements JsonParser<Box, JSONArray> {

    @Override
    public Box parse(JSONArray json) throws NominatimAPIResponseException {
        if (json.length() != 4) {
            throw new NominatimAPIResponseException("Box coordinates must be an array containing 4 values", json.toString());
        }
        Box box = new Box();
        box.left = json.getDouble(0);
        box.right = json.getDouble(1);
        box.top = json.getDouble(2);
        box.bottom = json.getDouble(3);
        return box;
    }

}
