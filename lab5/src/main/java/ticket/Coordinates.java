package ticket;

import java.util.Objects;

public class Coordinates {
    private Long x; //Значение поля должно быть больше -503, Поле не может быть null
    private Float y; //Поле не может быть null

    private static final long X_MIN = -502;

    public Coordinates(Long x, Float y) {
        if (x == null || y == null)
            throw new NullPointerException("x и y не могут быть null");
        if (x < X_MIN)
            throw new IllegalArgumentException("Значение поля x должно быть больше " + (X_MIN - 1));
        this.x = x;
        this.y = y;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Coordinates)) return false;
        Coordinates that = (Coordinates) o;
        return x.equals(that.x) && y.equals(that.y);
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    @Override
    public String toString() {
        return "Coordinates{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}
