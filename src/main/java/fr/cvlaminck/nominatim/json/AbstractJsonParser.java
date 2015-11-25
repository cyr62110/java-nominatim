package fr.cvlaminck.nominatim.json;

import fr.cvlaminck.nominatim.exceptions.NominatimAPIResponseException;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Collection;
import java.util.Collections;

public abstract class AbstractJsonParser<T>
    implements JsonParser<T, JSONObject> {

    private Collection<String> mandatoryFields;

    protected AbstractJsonParser(Collection<String> mandatoryFields) {
        if (mandatoryFields == null) {
            mandatoryFields = Collections.emptyList();
        }
        this.mandatoryFields = mandatoryFields;
    }

    @Override
    public T parse(JSONObject json) throws NominatimAPIResponseException {
        assertAllMandatoryFieldsAvailable(json);
        try {
            return doParseFromJson(json);
        } catch (JSONException | IllegalStateException ex) {
            throw new NominatimAPIResponseException(ex, json.toString());
        }
    }

    protected void assertAllMandatoryFieldsAvailable(JSONObject json) throws NominatimAPIResponseException {
        for (String mandatoryField : mandatoryFields) {
            if (!json.has(mandatoryField)) {
                throw new NominatimAPIResponseException(String.format("%s. Field '%s' is missing from the response.", NominatimAPIResponseException.MESSAGE, mandatoryField), json.toString());
            }
        }
    }

    protected abstract T doParseFromJson(JSONObject json) throws JSONException, NominatimAPIResponseException;
}
