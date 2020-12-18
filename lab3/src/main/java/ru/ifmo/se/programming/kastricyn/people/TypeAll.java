package ru.ifmo.se.programming.kastricyn.people;

public enum TypeAll {
    ALL("все"),
    NOT_NOTHING("никто"),
    EVERY("каждый"),
    EVERY_WHO("каждый, кто");


    private String name;

    TypeAll(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }

}
