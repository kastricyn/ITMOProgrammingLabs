package ru.itmo.se.programming.p3114.s311734.humans;

import ru.itmo.se.programming.p3114.s311734.thingOfMarket;

public interface IPeople {
     default void thinkAbout(Object thing, String verb){
          System.out.println(verb + thing.toString());
     }

     default boolean pay(int value) {return false;}
     default void pay(int value, String message){
          System.out.println(pay(value) ? message : "не " + message);
     }

     default boolean buy(thingOfMarket thing, String verb) {
          return thing.buy(verb);
     }


}
