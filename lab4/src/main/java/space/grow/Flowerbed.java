package space.grow;

import exceptions.MinMoreThenMaxException;

import java.util.Arrays;

public class Flowerbed {
    private static int count;

    {
        count++;
    }

    private String name;
    private Flower.Type flowerType;
    private Flower[] flowers;

    public Flowerbed(int minNumber, int maxNumber) throws MinMoreThenMaxException {
        if (minNumber > maxNumber)
            throw new MinMoreThenMaxException(minNumber, maxNumber);
        name = "клумба " + count;
        flowerType = Flower.Type.values()[(int) (Math.random() * Flower.Type.values().length)];
        int number = minNumber + (int) (Math.random() * (maxNumber - minNumber));
        flowers = new Flower[number];
        for (int i = 0; i < number; i++) {
            flowers[i] = new Flower(flowerType, this);
        }
    }

    public static Flowerbed[] getRandomArrayFlowerbeds(int min, int max, int minCropNumber, int maxCropNumber) throws MinMoreThenMaxException {
        if (min > max)
            throw new MinMoreThenMaxException(min, max);
        int n = min + (int) (Math.random() * (max - min));
        Flowerbed[] ans = new Flowerbed[n];
        for (int i = 0; i < n; i++) {
            ans[i] = new Flowerbed(minCropNumber, maxCropNumber);
        }
        return ans;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static int getCount() {
        return count;
    }

    public Flower.Type getFlowerType() {
        return flowerType;
    }

    public Flower[] getFlowers() {
        return flowers;
    }

    @Override
    public String toString() {
        return "Flowerbed{" +
                "name='" + name + '\'' +
                ", flowerType=" + flowerType +
                ", flowers=" + Arrays.toString(flowers) +
                '}';
    }
}
