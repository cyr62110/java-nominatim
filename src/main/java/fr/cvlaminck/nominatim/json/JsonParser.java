package fr.cvlaminck.nominatim.json;

import fr.cvlaminck.nominatim.exceptions.NominatimAPIResponseException;

public interface JsonParser<Result, Input> {

    public Result parse(Input json) throws NominatimAPIResponseException;

}
