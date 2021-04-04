package ru.ifmo.se.kastricyn.data;

import ru.ifmo.se.kastricyn.utility.Console;

import javax.xml.bind.annotation.XmlAttribute;
import java.util.Objects;

public class Coordinates {
    private Long x; //Значение поля должно быть больше -503, Поле не может быть null
    private Float y; //Поле не может быть null

    public static final long X_MIN = -502;
    private Coordinates(){}//for working JAXB

    public Coordinates(Long x, Float y) {
        setX(x).setY(y);
    }

    public Coordinates(Console console) {
        if (console.isShouldPrintHints()) {
            System.out.println("Создаём объект типа \"Coordinates\":");
            System.out.println("Поле x:");
        }
        setX(console.getLong(X_MIN));
        if (console.isShouldPrintHints())
            System.out.println("Поле y:");
        setY(console.getFloat());

        if (console.isShouldPrintHints())
            System.out.println("Создан объект: " + this);
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

    //All gets
    public Float getY() {
        return y;
    }

    public Long getX() {
        return x;
    }

    //All sets

    public Coordinates setX(Long x) {
        if (x == null)
            throw new NullPointerException();
        if (x < X_MIN)
            throw new IllegalArgumentException("Значение поля x должно быть больше " + (X_MIN - 1));
        this.x = x;
        return this;
    }

    public Coordinates setY(Float y) {
        if (y == null)
            throw new NullPointerException("y не может быть null");
        this.y = y;
        return this;
    }
}
