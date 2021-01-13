package dontKnowsSpace;

public enum Location {
    //todo: delete this enum
    SOON("скоро"),
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
