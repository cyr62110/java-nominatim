package fr.cvlaminck.nominatim.query.enums;

/**
 * Format that can be used by the nominatim API to build the response.
 * You will need to use this if you call the API by yourself instead of using {@link fr.cvlaminck.nominatim.Nominatim}
 * class.
 */
public enum ResponseFormat {
    HTML("html"),
    XML("xml"),
    JSON("json"),
    JSONV2("jsonv2");

    private String format = null;
    private ResponseFormat(String format) {
        this.format = format;
    }

    @Override
    public String toString() {
        return format;
    }
}
