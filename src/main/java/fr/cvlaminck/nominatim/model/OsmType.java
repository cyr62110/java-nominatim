package fr.cvlaminck.nominatim.model;

public enum OsmType {
    NODE("node", "N"),
    RELATION("relation", "R"),
    WAY("way", "W")
    ;

    private String name;
    private String prefix;
    private OsmType(String name, String prefix) {
        this.name = name;
        this.prefix = prefix;
    }

    public static OsmType typeForName(String name) {
        for (OsmType type : values()) {
            if (type.name.equals(name)) {
                return type;
            }
        }
        throw new IllegalStateException(String.format("'%s' is not one of the known OpenStreetMap type.", name));
    }

    public String getPrefix() {
        return prefix;
    }
}
