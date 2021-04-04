package ru.ifmo.se.kastricyn.exceptions;

public class NullException extends NullPointerException {
    public String toString () {
        return "Поле не может быть null";
    }
}
