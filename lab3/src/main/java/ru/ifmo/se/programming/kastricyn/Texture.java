package ru.ifmo.se.programming.kastricyn;

public enum Texture {
    SCABROUS("шеро");
    private String name;
    Texture (String name){
        this.name = name;
    }
    @Override
    public String toString() {return name;}
}
