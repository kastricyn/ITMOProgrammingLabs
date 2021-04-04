package ru.ifmo.se.kastricyn.exceptions;

public class EmptyStringException extends IllegalArgumentException{
    public String toString() {
        return "Строка не может быть пустой";
    }
}
