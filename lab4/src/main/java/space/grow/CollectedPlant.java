package space.grow;

import exceptions.CollectDelletedException;

import java.util.Objects;

public class CollectedPlant implements Growing {
    private static int count;

    private String name = "элемент уражая";
    private Seedbed seedbed;
    private Type type;
    private boolean deleted;
    private boolean ripe;

    {
        count++;
        name += " " + count;
        type = Type.values()[(int) (Math.random() * Type.values().length)];
        ripe = Math.random() < 0.5;
    }

    public CollectedPlant() {
    }

    public CollectedPlant(String name) {
        this.name = name;
    }

    public CollectedPlant(Type type, Seedbed seedbed) {
        this.type = type;
        this.seedbed = seedbed;
    }

    public CollectedPlant(String name, Type type) {
        this.name = name;
        this.type = type;
    }

    public CollectedPlant(String name, Type type, boolean isRipe) {
        this.name = name;
        this.type = type;
        this.ripe = isRipe;
    }


    public static int getCount() {
        return count;
    }

    public String getName() {
        return name;
    }

    public Seedbed getSeedbed() {
        return seedbed;
    }

    public Type getType() {
        return type;
    }

    public boolean deletable() {
        return !deleted;
    }

    public CollectedPlant delete() throws CollectDelletedException {
        if (!deletable())
            throw new CollectDelletedException(seedbed, this);
        deleted = true;
        return this;
    }

    public boolean isRipe() {
        return ripe;
    }


    public CollectedPlant setName(String name) {
        this.name = name;
        return this;
    }

    public boolean setSeedbed(Seedbed seedbed) {
        if (seedbed.getCropType().equals(getType()))
            this.seedbed = seedbed;
        else return false;
        return true;
    }

    public CollectedPlant setRipe(boolean ripe) {
        this.ripe = ripe;
        return this;
    }

    @Override
    public String toString() {
        return "Объект выращивания: \n" +
                "\t тип: " + type + "\n" +
                "\t имя: " + name + "\n" +
                "\t зрелый: " + (ripe ? "да" : "нет");
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CollectedPlant)) return false;
        CollectedPlant that = (CollectedPlant) o;
        return deleted == that.deleted && isRipe() == that.isRipe() && Objects.equals(getName(), that.getName()) && Objects.equals(getSeedbed(), that.getSeedbed()) && getType() == that.getType();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getSeedbed(), getType(), deleted, isRipe());
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
