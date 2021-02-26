package ru.ifmo.se.kastricyn.ticket;

import java.util.Objects;

public class Venue implements Comparable<Venue>{
    public static final int CAPACITY_MIN = 1;
    private static final long ID_MIN = 1;

    private static long nextId = ID_MIN;

    private long id; //Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
    private String name; //Поле не может быть null, Строка не может быть пустой
    private int capacity; //Значение поля должно быть больше 0
    private VenueType type; //Поле не может быть null
    private Address address; //Поле не может быть null

    private void initial(String name, int capacity, VenueType type, Address address) {
        if (name == null || type == null || address == null)
            throw new NullPointerException("Ни одно поле не может быть равно null");
        if (name.equals(""))
            throw new IllegalArgumentException("поле name не может быть пустым");
        if (capacity < CAPACITY_MIN)
            throw new IllegalArgumentException("Значение поля capacity должно быть больше " + (CAPACITY_MIN - 1));
        this.name = name;
        this.capacity = capacity;
        this.type = type;
        this.address = address;
    }

    public Venue(String name, int capacity, VenueType type, Address address) {
        initial(name, capacity, type, address);
        id = nextId++;
    }

    public Venue(long id, String name, int capacity, VenueType type, Address address) {
        initial(name, capacity, type, address);
        if (id < ID_MIN)
            throw new IllegalArgumentException("Значение поля id должно быть больше " + (ID_MIN - 1));
        if (id < nextId)
            throw new IllegalArgumentException("Значение поля id должно быть больше id предыдущего объекта этого типа ("
                    + (nextId - 1) + ")");
        this.id = id;
        nextId = id + 1;
    }

    public long getNextMinID(){
        return nextId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Venue)) return false;
        Venue venue = (Venue) o;
        return id == venue.id && capacity == venue.capacity && name.equals(venue.name) && type == venue.type && address.equals(venue.address);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, capacity, type, address);
    }

    @Override
    public String toString() {
        return "Venue{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", capacity=" + capacity +
                ", type=" + type +
                ", address=" + address +
                '}';
    }

    @Override
    public int compareTo(Venue o) {
        if(equals(o))
            return 0;
        else
            return capacity-o.capacity;
    }
}
