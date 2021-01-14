package space.grow;

import general.Size;

public class Flower implements Growable{
    private static int count;
    private String name = "цветок ";
    private Type type;
    private Flowerbed flowerbed;
    private Size sizeRelativeEarth;

    {count++;
        name +=" "+ count;
        type = Type.values()[(int)(Math.random()* Type.values().length)];
        sizeRelativeEarth = Size.TINY;
    }


    public Flower() {}
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Type getType() {
        return type;
    }

    public Size getSizeRelativeEarth() {
        return sizeRelativeEarth;
    }

    public static int getCount() {
        return count;
    }

    @Override
    public String toString() {
        return "Объект цветок: \n" +
                "\t тип: " + type + "\n" +
                "\t имя: " + name + "\n" +
                "\t размер относительно землянных растений: " + sizeRelativeEarth;
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
