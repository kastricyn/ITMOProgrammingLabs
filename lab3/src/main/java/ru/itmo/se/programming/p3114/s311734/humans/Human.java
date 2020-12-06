package ru.itmo.se.programming.p3114.s311734.humans;

public class Human {
    static int count;

    // -1 - unknown
    private int age = -1;
    //1 - isWoman, 0 - isMan, -1 - unknown
    private int isWoman = -1;
    private int money = 0;

    boolean canPay(int among) {
        return among <= money;
    }

    boolean pay(int among) {
        boolean paid;
        if (paid = canPay(among))
            money -= among;
        return paid;
    }

    public Human() { count++; }

    public Human(boolean isWoman, int age) {
        this.isWoman = isWoman ? 1 : 0;
        this.age = age;
    }


}
