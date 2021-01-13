package exceptions;

import space.grow.CollectedPlant;
import space.grow.Seedbed;

public class CollectDelletedException extends RuntimeException{
    private Seedbed seedbed;
    private CollectedPlant collectedPlant;
    public CollectDelletedException(Seedbed seedbed, CollectedPlant plant){
        this.seedbed = seedbed;
        this.collectedPlant = plant;
    }

    public CollectedPlant getCollectedPlant() {
        return collectedPlant;
    }

    public Seedbed getSeedbed() {
        return seedbed;
    }
}
