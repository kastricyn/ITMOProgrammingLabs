package space.grow;

import exceptions.MinMoreThenMaxException;
import space.Location;

import java.util.Arrays;
import java.util.Objects;

public class Seedbed implements Location {
    private static int count;

    private boolean isCultivated;
    private String name = "грядка";
    private CollectedPlant.Type cropType;
    private CollectedPlant[] crop;

    {
        count++;
        name += " " + count;
        cropType = CollectedPlant.Type.values()[(int) (Math.random() * CollectedPlant.Type.values().length)];

    }

    public Seedbed(int minCropNumber, int maxCropNumber) throws MinMoreThenMaxException {
        if (minCropNumber > maxCropNumber)
            throw new MinMoreThenMaxException(minCropNumber, maxCropNumber);

        int cropNumber = minCropNumber + (int) (Math.random() * (maxCropNumber - minCropNumber));
        crop = new CollectedPlant[cropNumber];
        for (int i = 0; i < cropNumber; i++)
            crop[i] = new CollectedPlant(cropType, this);
    }

    public static Seedbed[] getRandomSeedbeds(int minSeedbedsNumber, int maxSeedbedsNumber, int minCropNumber, int maxCropNumber) throws MinMoreThenMaxException {
        if (minSeedbedsNumber > maxSeedbedsNumber)
            throw new MinMoreThenMaxException(minSeedbedsNumber, maxSeedbedsNumber);
        int seedbedsNumber = minSeedbedsNumber + (int) (Math.random() * (maxSeedbedsNumber - minSeedbedsNumber));
        Seedbed[] ans = new Seedbed[seedbedsNumber];
        for (int i = 0; i < seedbedsNumber; i++)
            ans[i] = new Seedbed(minCropNumber, maxCropNumber);
        return ans;
    }

    public static int getCount() {
        return count;
    }

    public boolean canCultivate() {
        return !isCultivated;
    }

    public Seedbed setCultivated(boolean cultivated) {
        isCultivated = cultivated;
        return this;
    }

    public boolean cultivate() {
        boolean ans = canCultivate();
        isCultivated = true;
        System.out.println(getName() + (ans ? "> обработана" : "> была обработана до этого"));
        return ans;
    }

    public String getName() {
        return name;
    }

    public CollectedPlant.Type getCropType() {
        return cropType;
    }

    public CollectedPlant[] getCrop() {
        return crop;
    }


    public Seedbed setName(String name) {
        this.name = name;
        return this;
    }

    public Seedbed setCropType(CollectedPlant.Type cropType) {
        this.cropType = cropType;
        return this;
    }

    public boolean setCrop(CollectedPlant[] crop) {
        for (CollectedPlant c : crop)
            if (!c.getType().equals(getCropType())) {
                return false;
            }
        this.crop = crop;
        return true;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Seedbed)) return false;
        Seedbed seedbed = (Seedbed) o;
        return isCultivated == seedbed.isCultivated && Objects.equals(getName(), seedbed.getName()) && getCropType() == seedbed.getCropType() && Arrays.equals(getCrop(), seedbed.getCrop());
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(isCultivated, getName(), getCropType());
        result = 31 * result + Arrays.hashCode(getCrop());
        return result;
    }
}
