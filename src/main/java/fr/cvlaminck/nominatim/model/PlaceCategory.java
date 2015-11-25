package fr.cvlaminck.nominatim.model;

public enum PlaceCategory {
    BOUNDARY("boundary"),
    BUILDING("building"),
    MILITARY("military"),
    LANDUSE("landuse"),
    PLACE("place")
    ;

    private String name;
    private PlaceCategory(String name) {
        this.name = name;
    }

    public static PlaceCategory categoryForName(String name) {
        for (PlaceCategory cat : values()) {
            if (cat.name.equals(name)) {
                return cat;
            }
        }
        throw new IllegalStateException(String.format("'%s' is not one of the known category.", name));
    }
}
