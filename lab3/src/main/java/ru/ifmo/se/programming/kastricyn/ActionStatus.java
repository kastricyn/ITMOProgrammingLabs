package ru.ifmo.se.programming.kastricyn;

public enum ActionStatus {
    GLADLY("очень охотно"),
    GOODLY("прекрасно"),
    ONLY("исключительно"),
    PERMANENTLY("постоянно"),
    SOON("вскорости");

    private String name;
    ActionStatus (String name){
        this.name = name;
    }

    @Override
    public String toString(){
        return name;
    }

}
