package fr.cvlaminck.nominatim.exceptions;

/**
 * Thrown if the response send by nominatim cannot be parsed by the library.
 */
public class NominatimAPIResponseException extends NominatimAPIException {
    public static final String MESSAGE = "Response send by nominatim API cannot be parsed.";

    private String response = null;

    public NominatimAPIResponseException(String message, String response) {
        super(message);
        this.response = response;
    }

    public NominatimAPIResponseException(String response) {
        super(MESSAGE);
        this.response = response;
    }

    public NominatimAPIResponseException(Throwable cause, String response) {
        super(MESSAGE, cause);
        this.response = response;
    }

    public String getResponse() {
        return response;
    }
}
