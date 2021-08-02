package ru.ifmo.se.kastricyn.lab8.lib.data;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.ifmo.se.kastricyn.lab8.lib.utility.Console;

import java.io.Serializable;
import java.util.Objects;

/**
 * Надо для {@link Ticket}
 */
public class Coordinates implements Serializable {
    private static final long X_MIN = -502;
    private volatile @NotNull Long x; //Значение поля должно быть больше -503, Поле не может быть null
    private volatile @NotNull Float y; //Поле не может быть null

    /**
     * конструктор по умолчанию, для работы JAXB
     */
    private Coordinates() {
    }

    public Coordinates(Long x, Float y) {
        setX(x).setY(y);
    }

    public Coordinates(@NotNull Console console) {
        if (console.isInteractiveMode()) {
            System.out.println("Создаём объект типа \"Coordinates\":");
            System.out.println("Поле x:");
        }
        setX(console.getLong(X_MIN));
        if (console.isInteractiveMode())
            System.out.println("Поле y:");
        setY(console.getFloat());

        if (console.isInteractiveMode())
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
    public @NotNull String toString() {
        return "Coordinates{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }

    /**
     * @return true, если все поля заданы верно, иначе могут быть @exception
     */
    public synchronized boolean isExisting() {
        setX(x);
        setY(y);
        return true;
    }

    //All gets
    public @NotNull Float getY() {
        return y;
    }

    public @NotNull Coordinates setY(@Nullable Float y) {
        if (y == null)
            throw new NullPointerException("y не может быть null");
        this.y = y;
        return this;
    }

    //All sets

    public @NotNull Long getX() {
        return x;
    }

    public @NotNull Coordinates setX(@Nullable Long x) {
        if (x == null)
            throw new NullPointerException();
        if (x < X_MIN)
            throw new IllegalArgumentException("Значение поля x должно быть больше " + (X_MIN - 1));
        this.x = x;
        return this;
    }
}
