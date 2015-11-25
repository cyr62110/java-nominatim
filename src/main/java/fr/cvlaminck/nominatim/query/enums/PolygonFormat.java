package fr.cvlaminck.nominatim.query.enums;

public enum PolygonFormat {
    GEOJSON("polygon_geojson"),
    KML("polygon_kml"),
    SVG("polygon_svg"),
    TEXT("polygon_text");

    private String parameterName = null;
    private PolygonFormat(String parameterName) {
        this.parameterName = parameterName;
    }

    public String getParameterName() {
        return parameterName;
    }
}
