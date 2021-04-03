package ru.ifmo.se.kastricyn.ticket;

import ru.ifmo.se.kastricyn.TryAgain;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Arrays;
import java.util.Objects;
import java.util.Scanner;

public class Venue implements Comparable<Venue> {
    public static final int CAPACITY_MIN = 1;
    private static long nextId = 1; //id не может быть меньше 1

    private final long id; //Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
    private String name; //Поле не может быть null, Строка не может быть пустой
    private int capacity; //Значение поля должно быть больше 0
    private VenueType type; //Поле не может быть null
    private Address address; //Поле не может быть null

    private void initial(String name, int capacity, VenueType type, Address address) {
        setName(name).setCapacity(capacity).setType(type).setAddress(address);
    }

    public Venue(String name, int capacity, VenueType type, Address address) {
        initial(name, capacity, type, address);
        id = nextId++;
    }

    public Venue(long id, String name, int capacity, VenueType type, Address address) {
        initial(name, capacity, type, address);
        if (id < nextId)
            throw new IllegalArgumentException("Значение поля id должно быть больше id предыдущего объекта этого типа/минимально возможного значения ("
                    + (nextId - 1) + ")");
        this.id = id;
        nextId = id + 1;
    }

    private Venue() {
        id = nextId++;
    }

    public static Venue getVenue(Scanner in, boolean shouldPrintHints) {
        Venue venue = new Venue();
        if (shouldPrintHints) {
            System.out.println("Создаём объект типа \"Venue\":");
            System.out.println("Введите пожалуйста имя:");
        }
        while (true)
            try {
                venue.setName(in.nextLine().trim());
                break;
            } catch (RuntimeException e) {
                TryAgain.printErrors(shouldPrintHints, e);
            }

        if (shouldPrintHints)
            System.out.println("Введите поле capacity:");
        while (true)
            try {
                venue.setCapacity(Integer.parseInt(in.nextLine().trim()));
                break;
            } catch (RuntimeException e) {
                TryAgain.printErrors(shouldPrintHints, e);
            }

        if (shouldPrintHints) {
            String str = Arrays.toString(VenueType.values());
            str = str.substring(1, str.length() - 1);
            System.out.println("Введите поле type (возможны следующие варианты: " + str + " ):\n");
        }
        while (true)
            try {
                venue.setType(VenueType.valueOf(in.nextLine().trim()));
                break;
            } catch (RuntimeException e) {
                TryAgain.printErrors(shouldPrintHints, e);
            }

        venue.setAddress(Address.getAddress(in, shouldPrintHints));

        if (shouldPrintHints)
            System.out.println("Создан объект: " + venue);

        return venue;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Venue)) return false;
        Venue venue = (Venue) o;
        return capacity == venue.capacity && name.equals(venue.name) && type == venue.type && address.equals(venue.address);
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
        if (equals(o))
            return 0;
        else
            return (capacity - o.getCapacity())*type.compareTo(o.getType())*address.compareTo(o.getAddress())*name.compareTo(o.getName());
    }


    // All gets
    public static long getNextAvailableId() {
        return nextId;
    }
@XmlAttribute
    public long getId() {
        return id;
    }
    public Address getAddress() {
        return address;
    }
    public int getCapacity() {
        return capacity;
    }
    public String getName() {
        return name;
    }
    public VenueType getType() {
        return type;
    }

    //All sets

    public Venue setAddress(Address address) {
        if (address == null)
            throw new NullPointerException("Поле не может быть null");
        this.address = address;
        return this;
    }

    public Venue setCapacity(int capacity) {
        if (capacity < CAPACITY_MIN)
            throw new IllegalArgumentException("Значение поля capacity должно быть больше " + (CAPACITY_MIN - 1));
        this.capacity = capacity;
        return this;
    }

    public Venue setName(String name) {
        if (name == null)
            throw new NullPointerException("Поле name не может быть null");
        if (name.equals(""))
            throw new IllegalArgumentException("name не может быть пустым");
        this.name = name;
        return this;
    }

    public Venue setType(VenueType type) {
        if (name == null)
            throw new NullPointerException("Поле не может быть null");
        this.type = type;
        return this;
    }

}
