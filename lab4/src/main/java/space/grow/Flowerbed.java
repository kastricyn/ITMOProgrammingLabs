package space.grow;

import exceptions.MinMoreThenMaxException;

public class Flowerbed {
    private static int count;

    {
        count++;
    }

    private boolean isCultivated;
    private String name;
    private CollectedPlant.Type cropType;
    private CollectedPlant[] crop;

    public Flowerbed(int minCropNumber, int maxCropNumber) throws MinMoreThenMaxException {
        if (minCropNumber > maxCropNumber)
            throw new MinMoreThenMaxException(minCropNumber, maxCropNumber);
        name = "клумба " + count;
        cropType = CollectedPlant.Type.values()[(int) (Math.random() * CollectedPlant.Type.values().length)];
        int number = minCropNumber + (int) (Math.random() * (maxCropNumber - minCropNumber));
        crop = new CollectedPlant[number];
        for (int i = 0; i < number; i++) {
            crop[i] = new CollectedPlant(cropType, this);
        }
    }

    public static Flowerbed[] getRandomArraySeedbeds(int min, int max, int minCropNumber, int maxCropNumber) throws MinMoreThenMaxException {
        if (min > max)
            throw new MinMoreThenMaxException(min, max);
        int n = min + (int) (Math.random() * (max - min));
        Flowerbed[] ans = new Flowerbed[n];
        for (int i = 0; i < n; i++) {
            ans[i] = new Flowerbed(minCropNumber, maxCropNumber);
        }
        return ans;
    }

    public boolean canCultivate() {
        return !isCultivated;
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

    public CollectedPlant.Type getCropType() {
        return cropType;
    }

    public CollectedPlant[] getCrop() {
        return crop;
    }

    public void setCropType(CollectedPlant.Type cropType) {
        this.cropType = cropType;
    }
}
