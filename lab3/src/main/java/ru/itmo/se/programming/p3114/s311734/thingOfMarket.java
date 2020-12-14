package ru.itmo.se.programming.p3114.s311734;

public interface thingOfMarket{
    void setPrice();
    int getPrice();

    default boolean buy(String verb){
        System.out.println("не " + verb);
        return false;
    }

    boolean sell();


}
