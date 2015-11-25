package fr.cvlaminck.nominatim.model;

public enum PlaceType {
    AIRFIELD("airfield"),
    ADMINISTRATIVE("administrative"),
    CITY("city"),
    HAMLET("hamlet"),
    LOCALITY("locality"),
    RESIDENTIAL("residential"),
    TOWN("town"),
    VILLAGE("village"),
    YES("yes") //Can be returned by the API -> ???
    ;

    private String name;
    private PlaceType(String name) {
        this.name = name;
    }

    public static PlaceType typeForName(String name) {
        for (PlaceType type : values()) {
            if (type.name.equals(name)) {
                return type;
            }
        }
        throw new IllegalStateException(String.format("'%s' is not one of the known place type.", name));
    }
}
