package ru.ifmo.se.programming.kastricyn;

public enum City {
    Davilon("Давилон"),
    Grabenberg("Грабенберг"),
    Other("другой");

    private String title;

    City(String title){
        this.title = title;
    }

    @Override
    public String toString() {return title;}

}