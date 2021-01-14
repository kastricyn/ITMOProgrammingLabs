package space.grow;

import exceptions.MinMoreThenMaxException;
import space.Locationable;

import java.util.Arrays;

public class Seedbed implements Locationable {
    private static int count;

    {
        count++;
    }

    private boolean isCultivated;
    private String name;
    private CollectedPlant.Type cropType;
    private CollectedPlant[] crop;

    public Seedbed(int minCropNumber, int maxCropNumber) throws MinMoreThenMaxException {
        if (minCropNumber > maxCropNumber)
            throw new MinMoreThenMaxException(minCropNumber, maxCropNumber);
        name = "грядка " + count;
        cropType = CollectedPlant.Type.values()[(int) (Math.random() * CollectedPlant.Type.values().length)];
        int number = minCropNumber + (int) (Math.random() * (maxCropNumber - minCropNumber));
        crop = new CollectedPlant[number];
        for (int i = 0; i < number; i++) {
            crop[i] = new CollectedPlant(cropType, this);
        }
    }

    public static Seedbed[] getRandomArraySeedbeds(int min, int max, int minCropNumber, int maxCropNumber) throws MinMoreThenMaxException {
        if (min > max)
            throw new MinMoreThenMaxException(min, max);
        int n = min + (int) (Math.random() * (max - min));
        Seedbed[] ans = new Seedbed[n];
        for (int i = 0; i < n; i++) {
            ans[i] = new Seedbed(minCropNumber, maxCropNumber);
        }
        return ans;
    }

    public boolean canCultivate() {
        return !isCultivated;
    }

    public boolean cultivate() {
        boolean ans = canCultivate();
        isCultivated = true;
        if (ans)
            System.out.println(getName() + (ans ? "> обработана" : "> была обработана до этого"));
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

    public CollectedPlant.Type getCropType() {
        return cropType;
    }

    public CollectedPlant[] getCrop() {
        return crop;
    }

    public void setCropType(CollectedPlant.Type cropType) {
        this.cropType = cropType;
    }

    @Override
    public String toString() {
        return "Seedbed{" +
                "isCultivated=" + isCultivated +
                ", name='" + name + '\'' +
                ", cropType=" + cropType +
                ", crop=" + Arrays.toString(crop) +
                '}';
    }
}
