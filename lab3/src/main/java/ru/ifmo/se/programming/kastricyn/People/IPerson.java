package ru.ifmo.se.programming.kastricyn.People;

import ru.ifmo.se.programming.kastricyn.City;
import ru.ifmo.se.programming.kastricyn.Saleable;

public interface IPerson extends IPeople {
    void setHomeCity(City homeCity);
    City getHomeCity();
    String inhabitantCity(City city);
    String buy( String verb, Saleable thing);
}
