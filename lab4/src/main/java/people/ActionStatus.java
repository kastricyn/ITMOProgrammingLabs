package people;

public enum ActionStatus {
    GLADLY("очень охотно"),
    GOODLY("прекрасно"),
    ONLY("исключительно"),
    PERMANENTLY("постоянно"),
    SOON("скоро"),
    START("начал"),
    NOT("не"),
    IMMEDIATELY("сразу"),
    ;

    private String name;

    ActionStatus(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }

}
