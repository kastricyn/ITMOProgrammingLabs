package dontKnowsSpace.people;


import dontKnowsSpace.place.GrowPlace;

public interface WorkableInSeadbeds {
    default void getToWork() {
        System.out.println(this + " принялся за работу");}

    default void crawl(Object when){
        System.out.println(this + " ползаю в(среди) " + when);
    }

    default void collect(GrowPlace.GrowObj obj){

    }
}
