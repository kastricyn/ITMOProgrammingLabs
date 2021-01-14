package people;

public enum Color {
    PINK("розовый"),
    RED("красный"),
    ;

    private String title;

    Color(String title) {
        this.title = title;
    }

    public String toString() {
        return title;
    }
}