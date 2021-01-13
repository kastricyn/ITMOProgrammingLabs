package space.grow;

import exceptions.CollectDelletedException;

public class CollectedPlant {
    private static int count;
    {count++;}
    private String name;
    private Type type;
    private boolean deleted;
    private boolean ripe;
    private Seedbed seedbed;

    public CollectedPlant() {
        name = "элемент уражая " + count;
        type = Type.values()[(int)(Math.random()* Type.values().length)];
        ripe = Math.random()<0.5;
    }
    public CollectedPlant(String name) {
        this.name = name;
        type = Type.values()[(int)(Math.random()* Type.values().length)];
        ripe = Math.random()<0.5;
    }
    public CollectedPlant(Type type, Seedbed seedbed) {
        name = "элемент уражая " + count;
        this.type = type;
        this.seedbed = seedbed;
        ripe = Math.random()<0.5;
    }
    public CollectedPlant(String name, Type type) {
        this.name = name;
        this.type = type;
        ripe = Math.random()<0.5;
    }
    public CollectedPlant(String name, Type type, boolean isRipe){
        this.name = name;
        this.type = type;
        this.ripe = isRipe;
    }

    public boolean isRipe() {
        return ripe;
    }

    public boolean deletable(){
        return !deleted;
    }

    public void setDeleted() throws CollectDelletedException {
        if(deleted)
            throw new CollectDelletedException(seedbed, this);
        deleted = true;
    }

    public void setRipe(boolean ripe) {
        this.ripe = ripe;
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
        return "Объект выращивания: \n" +
                "\t тип: " + type + "\n" +
                "\t имя: " + name + "\n" +
                "\t зрелый: " + (ripe ? "да" : "нет");
    }
    public enum Type {
        CUCUMBERS("огурцы"),
        TOMATOES("помидоры"),
        MOON_STRAWBERRY("лунная клубника"),
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
