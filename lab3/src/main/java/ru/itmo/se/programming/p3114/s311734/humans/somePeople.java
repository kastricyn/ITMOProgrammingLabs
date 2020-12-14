package ru.itmo.se.programming.p3114.s311734.humans;

import ru.itmo.se.programming.p3114.s311734.thingOfMarket;

public class somePeople implements IPeople{
    private Human people[];

    public int getCount() {return people.length;}
    public Human[] getPeople(){return people;}
    public Human getHuman(int idx){return people[idx];}


    public somePeople(){
        int min = 1;
        int max = 100;
        people = new Human[min+(int)(Math.random()*(max-min))];
        initialPeople();
    }
    public somePeople(int min, int max){
        people = new Human[min+(int)(Math.random()*(max-min))];
        initialPeople();
    }
    public somePeople(int max){
        people = new Human[(int)(Math.random()*(max))];
        initialPeople();
    }
    private void initialPeople(){
        for (int i = 0; i < people.length; i++)
            people[i] = new Person();
    }





//    @Override
    public boolean pay(int value) {
        return false;
    }

}
