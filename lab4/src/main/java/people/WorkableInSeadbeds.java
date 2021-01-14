package people;


import space.Location;
import space.grow.CollectedPlant;

public interface WorkableInSeadbeds {

    default void crawl(Location when){
        System.out.println(this + " ползает в(среди) " + when.getName());
    }

    default void collected(CollectedPlant plant) {
        plant.delete();
        System.out.println(this + " собрал " + plant);
    }
}
