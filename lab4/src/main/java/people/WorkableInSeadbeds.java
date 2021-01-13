package people;


import space.Location;
import space.grow.CollectedPlant;

public interface WorkableInSeadbeds {
    default void getToWork() {
        System.out.println(this + " принялся за работу");}

    default void crawl(Location when){
        System.out.println(this + " ползает в(среди) " + when);
    }

    default void collected(CollectedPlant plant) {
        plant.setDeleted();
        System.out.println(this + " собрал " + plant);
    }
}
