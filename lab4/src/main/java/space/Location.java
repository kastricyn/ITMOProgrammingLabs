package space;

public enum Location {
    HILL("холм"),
    VERANDA("веранда"),
    TABLE("столом"),
    SEEDBEDS("грядки"),
    ;

    private String name;

    Location(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
