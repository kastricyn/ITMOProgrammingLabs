package ru.itmo.se.programming.p3114.s311734;

public class Flower {
    private String name;
    private Size size = Size.NORMAL;

    Flower(String name, Size size) {
        this.name = name;
        this.size = size;
    }
    Flower(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setSize(Size size) {
        this.size = size;
    }
}
