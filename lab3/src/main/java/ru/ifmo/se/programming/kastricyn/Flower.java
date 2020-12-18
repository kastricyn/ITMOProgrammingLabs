package ru.ifmo.se.programming.kastricyn;

import java.util.Objects;

public class Flower {
    private String name;

    Flower(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Flower)) return false;
        Flower flower = (Flower) o;
        return name.equals(flower.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}