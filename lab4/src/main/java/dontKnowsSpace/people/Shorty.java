package dontKnowsSpace.people;

import dontKnowsSpace.exсeptions.CantPokeException;

public class Shorty extends Human implements WorkableInSeadbeds{
    public void poke(Human human, Object by) throws CantPokeException {
        if(Math.random()<0.5)
            throw new CantPokeException(CantPokeException.Reason.values()[
                    //выбираем рандомную константу из массива всех констант Enum
                    (int)(Math.random()*CantPokeException.Reason.values().length)
                    ]);
        System.out.println(this + " ткнул " + human +" используя объект "+ by);
    }

    public Shorty(String name){
        super();
        super.setName(name);
    }

    @Override
    public String toString() {
        return "Коротышка " + super.toString();
    }
}
