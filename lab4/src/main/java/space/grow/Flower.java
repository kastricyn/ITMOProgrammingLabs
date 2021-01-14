package space.grow;

public class Flower {
    private static int count;
    {count++;}
    private String name;
    private Type type;
    private Flowerbed flowerbed;

    public Flower() {
        name = "цветок " + count;
        type = Type.values()[(int)(Math.random()* Type.values().length)];
    }
    public Flower(String name) {
        this.name = name;
        type = Type.values()[(int)(Math.random()* Type.values().length)];
    }
    public Flower(Type type, Flowerbed flowerbed) {
        name = "цветок " + count;
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

    public static int getCount() {
        return count;
    }

    @Override
    public String toString() {
        return "Объект цветок: \n" +
                "\t тип: " + type + "\n" +
                "\t имя: " + name + "\n";
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
