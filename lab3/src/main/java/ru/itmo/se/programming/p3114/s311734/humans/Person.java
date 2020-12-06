package ru.itmo.se.programming.p3114.s311734.humans;

import ru.itmo.se.programming.p3114.s311734.City;
import ru.itmo.se.programming.p3114.s311734.Size;

public class Person extends Human{
    private String accost;
    private String lastName;
    private City homeCity;
    private City currentCity;
    private Size beauty;

    void setAccost(String accost){this.accost = accost;}
    void setLastName(String lastName){this.lastName = lastName;}
    void setHomeCity(City homeCity){this.homeCity = homeCity;}
    void setCurrentCity(City currentCity){this.currentCity = currentCity;}
    void setBeauty(Size beauty){this.beauty = beauty;}

}
