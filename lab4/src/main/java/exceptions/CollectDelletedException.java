package exceptions;

import space.grow.CollectedPlant;
import space.grow.Seedbed;

import java.util.Objects;

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

    @Override
    public String toString() {
        return "CollectDelletedException{" +
                "seedbed=" + seedbed +
                ", collectedPlant=" + collectedPlant +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CollectDelletedException)) return false;
        CollectDelletedException that = (CollectDelletedException) o;
        return Objects.equals(getSeedbed(), that.getSeedbed()) && Objects.equals(getCollectedPlant(), that.getCollectedPlant());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getSeedbed(), getCollectedPlant());
    }
}
