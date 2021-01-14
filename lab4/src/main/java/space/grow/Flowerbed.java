package space.grow;

import exceptions.MinMoreThenMaxException;

import java.util.Arrays;
import java.util.Objects;

public class Flowerbed {
    private static int count;

    private String name = "клумба";
    private Flower.Type flowerType;
    private Flower[] flowers;

    {
        count++;
        name += " " + count;
        flowerType = Flower.Type.values()[(int) (Math.random() * Flower.Type.values().length)];

    }

    public Flowerbed(int minNumber, int maxNumber) throws MinMoreThenMaxException {
        if (minNumber > maxNumber)
            throw new MinMoreThenMaxException(minNumber, maxNumber);

        int flowersNumber = minNumber + (int) (Math.random() * (maxNumber - minNumber));
        flowers = new Flower[flowersNumber];
        for (int i = 0; i < flowersNumber; i++)
            flowers[i] = new Flower(flowerType, this);
    }

    public static Flowerbed[] getRandomFlowerbeds(int minFlowerbedsNumber, int maxFlowerbedsNumber, int minCropNumber, int maxCropNumber) throws MinMoreThenMaxException {
        if (minFlowerbedsNumber > maxFlowerbedsNumber)
            throw new MinMoreThenMaxException(minFlowerbedsNumber, maxFlowerbedsNumber);
        int flowerbedsNumber = minFlowerbedsNumber + (int) (Math.random() * (maxFlowerbedsNumber - minFlowerbedsNumber));
        Flowerbed[] ans = new Flowerbed[flowerbedsNumber];
        for (int i = 0; i < flowerbedsNumber; i++)
            ans[i] = new Flowerbed(minCropNumber, maxCropNumber);
        return ans;
    }

    public static int getCount() {
        return count;
    }

    public String getName() {
        return name;
    }

    public Flower.Type getFlowerType() {
        return flowerType;
    }

    public Flower[] getFlowers() {
        return flowers;
    }


    public Flowerbed setName(String name) {
        this.name = name;
        return this;
    }

    public Flowerbed setFlowerType(Flower.Type flowerType) {
        this.flowerType = flowerType;
        return this;
    }

    public boolean setflowers(Flower[] flowers) {
        for (Flower f : flowers)
            if (!f.getType().equals(getFlowerType())) {
                return false;
            }
        this.flowers = flowers;
        return true;
    }

    @Override
    public String toString() {
        return "Flowerbed{" +
                "имя='" + name + '\'' +
                ", тип цветов=" + flowerType +
                ", кол-во цветов=" + flowers.length +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Flowerbed)) return false;
        Flowerbed flowerbed = (Flowerbed) o;
        return Objects.equals(getName(), flowerbed.getName()) && getFlowerType() == flowerbed.getFlowerType() && Arrays.equals(getFlowers(), flowerbed.getFlowers());
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(getName(), getFlowerType());
        result = 31 * result + Arrays.hashCode(getFlowers());
        return result;
    }
}
