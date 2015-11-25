package fr.cvlaminck.nominatim.exceptions;

public abstract class NominatimAPIException extends Exception {
    protected NominatimAPIException() {
    }

    protected NominatimAPIException(String message) {
        super(message);
    }

    protected NominatimAPIException(String message, Throwable cause) {
        super(message, cause);
    }

    protected NominatimAPIException(Throwable cause) {
        super(cause);
    }

    protected NominatimAPIException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
