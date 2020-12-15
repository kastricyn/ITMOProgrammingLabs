package ru.ifmo.se.programming.kastricyn.People;

public abstract class Human implements IPeople {
    private static int count;
    private String name;

    {
        count++;
    }

    public static int getCount() {
        return count;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


}
