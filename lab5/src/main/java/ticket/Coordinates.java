package ticket;

public class Coordinates {
    private Long x; //Значение поля должно быть больше -503, Поле не может быть null
    private Float y; //Поле не может быть null

    public Coordinates(Long x, Float y) {
        if (x == null || y == null)
            throw new NullPointerException("x и y не могут быть null");
        if (x <= -503)
            throw new IllegalArgumentException("Значение поля x должно быть больше -503");
        this.x = x;
        this.y = y;
    }
}
