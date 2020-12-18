package ru.ifmo.se.programming.kastricyn.people;

import ru.ifmo.se.programming.kastricyn.City;
import ru.ifmo.se.programming.kastricyn.Saleable;
import ru.ifmo.se.programming.kastricyn.Size;

public class Persons extends SomePeople implements IPerson {
    private City homeCity = City.Other;

    @Override
    public void setHomeCity(City homeCity) {
        this.homeCity = homeCity;
    }

    @Override
    public City getHomeCity() {
        return homeCity;
    }

    @Override
    public String inhabitantCity(City city) {
        if (homeCity.equals(city))
            return "жители " + city.toString() + "а";
        else return "приезжие из других городов";
    }

    @Override
    public String buy(String verb, Saleable thing) {
        String answ = verb;
        if (!thing.toString().isEmpty())
            answ += " " + thing.toString();
        return answ;
    }

    public String differ(String verb, Size size, Object thing) {
        return (verb + " " + size.toString() + " " + thing.toString());
    }


}
