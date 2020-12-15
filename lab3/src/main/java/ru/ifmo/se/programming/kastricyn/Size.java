package ru.ifmo.se.programming.kastricyn;

public enum Size {
    EMPTY(""),
    TINY("малюсенький"),
    SMALL("маленкий"),
    NORMAL("нормальный"),
    BIG("большой"),
    LARGE("крупный"),
    GIGANTIC("гигантский"),
    HUGE("огромный");

    private String title;
    Size(String title){this.title = title;}
    public String toString(){return title;}
}