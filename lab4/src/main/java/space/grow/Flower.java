package space.grow;

import general.Size;

import java.util.Objects;

public class Flower implements Growing {
    private static int count;

    private String name = "цветок";
    private Type type;
    private Flowerbed flowerbed;
    private Size sizeRelativeEarth;

    {
        count++;
        name += " " + count;
        type = Type.values()[(int) (Math.random() * Type.values().length)];
        sizeRelativeEarth = Size.TINY;
    }


    public Flower() {
    }

    public Flower(String name) {
        this.name = name;
    }

    public Flower(Type type, Flowerbed flowerbed) {
        this.type = type;
        this.flowerbed = flowerbed;
    }

    public Flower(String name, Type type) {
        this.name = name;
        this.type = type;
    }


    public static int getCount() {
        return count;
    }


    public String getName() {
        return name;
    }

    public Type getType() {
        return type;
    }

    public Size getSizeRelativeEarth() {
        return sizeRelativeEarth;
    }


    public Flower setName(String name) {
        this.name = name;
        return this;
    }

    public Flower setType(Type type) {
        this.type = type;
        return this;
    }

    public boolean setFlowerbed(Flowerbed flowerbed) {
        if (flowerbed.getFlowerType().equals(getType()))
            this.flowerbed = flowerbed;
        else return false;
        return true;
    }

    public Flower setSizeRelativeEarth(Size sizeRelativeEarth) {
        this.sizeRelativeEarth = sizeRelativeEarth;
        return this;
    }

    @Override
    public String toString() {
        return "Объект цветок: \n" +
                "\t тип: " + type + "\n" +
                "\t имя: " + name + "\n" +
                "\t размер относительно землянных растений: " + sizeRelativeEarth;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Flower)) return false;
        Flower flower = (Flower) o;
        return Objects.equals(getName(), flower.getName()) && getType() == flower.getType() && Objects.equals(flowerbed, flower.flowerbed) && getSizeRelativeEarth() == flower.getSizeRelativeEarth();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getType(), flowerbed, getSizeRelativeEarth());
    }

    public enum Type {
        MOON_DAISY("маргаритка"),
        PANSIES("анютины глазки"),
        NASTURTIUM("настурции"),
        MOON_MIGNONETTE("лунная резеда"),
        ASTRA("астра"),
        ;

        private String name;

        Type(String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return name;
        }
    }


}
