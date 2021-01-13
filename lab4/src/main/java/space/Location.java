package space;

public enum Location {
    HILL("холм"),
    VERANDA("веранда"),
    TABLE("столом")
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
