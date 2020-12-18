package ru.ifmo.se.programming.kastricyn.people.face;

public class Face {
    private String weight = "";

    public String getWeight() {
        return weight;
    }

    public Face setWeight(String weight) {
        this.weight = weight;
        return this;
    }

    private String name = "лицо";

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    private Eyes eyes;

    private Nose nose;

    private Cheeks cheeks;

    public Face(Eyes eyes, Nose nose, Cheeks cheeks) {
        this.eyes = eyes;
        this.nose = nose;
        this.cheeks = cheeks;
    }

    @Override
    public String toString() {
        return weight + " " + name + " c " + eyes.toString().replaceFirst("ий ", "ими ").
                replaceFirst("и, ", "ами, ") + " и " +
                nose.getWeight().replaceFirst("ий", "им") + ", " + nose.clutch("зажатым ") +
                "между " + cheeks.toString().replaceFirst("е ", "умя ").
                replaceFirst("е ", "ми ").replaceFirst("и$", "ами ") +
                nose.toString() + "ом"
                ;
    }
}
