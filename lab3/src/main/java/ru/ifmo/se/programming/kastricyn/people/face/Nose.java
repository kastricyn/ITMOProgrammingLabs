package ru.ifmo.se.programming.kastricyn.people.face;

public class Nose {
    String weight = "";
    String name = "нос";

    public String getWeight() {
        return weight;
    }

    public Nose setWeight(String weight) {
        this.weight = weight;
        return this;
    }


    public String getName() {
        return name;
    }

    public Nose setName(String name) {
        this.name = name;
        return this;
    }

    public String clutch(String verb) {
        return verb;
    }

    @Override
    public String toString() {
        return name;
    }
}
