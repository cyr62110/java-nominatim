package fr.cvlaminck.nominatim.exceptions;

/**
 * Runtime exception thrown when you are trying to build an non valid query using one of the query builder.
 */
public class NominatimAPIProtocolException extends RuntimeException {
    public NominatimAPIProtocolException(String message) {
        super(message);
    }

    public NominatimAPIProtocolException(String messageFormat, Object... args) {
        this(String.format(messageFormat, args));
    }
}
