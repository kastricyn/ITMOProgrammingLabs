package ru.ifmo.se.programming.kastricyn.People;

import ru.ifmo.se.programming.kastricyn.ActionStatus;
import ru.ifmo.se.programming.kastricyn.Saleable;

import java.util.Arrays;
import java.util.Objects;

public class somePeople implements IPeople{
    private Human people[];
    private String name = "Какие-то люди";

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

    @Override
    public String toString() {
        return name;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

//    @Override
    public String pay(String wages, Saleable thing, String verb, ActionStatus desire) {
        String answ = "";
        if(!desire.toString().isEmpty())
            answ += desire.toString();
        answ += " " + verb;
        if(!wages.isEmpty())
            answ += " " + wages;
        if(!thing.toString().isEmpty())
            answ += " за " + thing.toString(); //TODO: thing
        return answ;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof somePeople)) return false;
        somePeople that = (somePeople) o;
        return Arrays.equals(getPeople(), that.getPeople()) && Objects.equals(getName(), that.getName());
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(getName());
        result = 31 * result + Arrays.hashCode(getPeople());
        return result;
    }
}