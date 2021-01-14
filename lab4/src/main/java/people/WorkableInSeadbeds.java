package people;


import space.Locationable;
import space.grow.CollectedPlant;

public interface WorkableInSeadbeds {
    default void getToWork() {
        System.out.println(this + " принялся за работу");}

    default void crawl(Locationable when){
        System.out.println(this + " ползает в(среди) " + when.getName());
    }

    default void collected(CollectedPlant plant) {
        plant.setDeleted();
        System.out.println(this + " собрал " + plant);
    }
}
