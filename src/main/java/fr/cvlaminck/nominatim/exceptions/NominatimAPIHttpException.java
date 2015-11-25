package fr.cvlaminck.nominatim.exceptions;

/**
 * Thrown if the API respond an other HTTP code than 200 OK.
 */
public class NominatimAPIHttpException extends NominatimAPIException {
    private final static String MESSAGE = "Nominatim API responded with %d http response code";

    public NominatimAPIHttpException(int responseCode) {
        super(String.format(MESSAGE, responseCode));
    }
}
