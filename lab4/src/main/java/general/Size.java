package general;

public enum Size {
    EMPTY(""),
    NONE("нет"),
    TINY("малюсенький"),
    SMALL("маленкий"),
    NORMAL("нормальный"),
    BIG("большой"),
    LARGE("крупный"),
    GIGANTIC("гигантский"),
    HUGE("огромный");

    private String title;

    Size(String title) {
        this.title = title;
    }

    public String toString() {
        return title;
    }
}
