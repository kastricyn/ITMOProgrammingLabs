package ru.itmo.se.programming.p3114.s311734.humans;

import ru.itmo.se.programming.p3114.s311734.thingOfMarket;

public abstract class Human implements IPeople {
    private static int count;
    private String name;
    private boolean isWoman;
    private int money = 0;
Object a = new Object();

    {
        count++;
    }

    public Human() {
    }

    public static int getCount() {
        return count;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean canPay(int value) {
        return value <= money;
    }

    public boolean pay(int value) {
        boolean paid = canPay(value);
        if (paid)
            money -= value;
        return paid;
    }

    public final boolean buy(thingOfMarket thing, String verb) {
        return thing.buy(verb);
    }


}
