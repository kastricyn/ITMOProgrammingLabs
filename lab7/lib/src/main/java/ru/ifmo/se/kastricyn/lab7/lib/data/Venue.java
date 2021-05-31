package ru.ifmo.se.kastricyn.lab7.lib.data;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.ifmo.se.kastricyn.lab7.lib.utility.Console;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import java.io.Serializable;
import java.util.Objects;

/**
 * Нужен для {@link Ticket}
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class Venue implements Comparable<Venue>, Serializable {
    public static final int CAPACITY_MIN = 1;
    private static long nextId = 1; //id не может быть меньше 1

    @XmlAttribute
    private final long id; //Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
    private @Nullable String name; //Поле не может быть null, Строка не может быть пустой
    private int capacity; //Значение поля должно быть больше 0
    private @Nullable VenueType type; //Поле не может быть null
    private @Nullable Address address; //Поле не может быть null

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

    /**
     * конструктор по умолчанию, для работы JAXB
     */
    private Venue() {
        id = nextId++;
    }

    /**
     * Конструктор для верного задания автоматически присваиваемых параметров
     */
    public Venue(@NotNull Venue venue) {
        initial(venue.getName(), venue.getCapacity(), venue.getType(), venue.getAddress());
        id = nextId++;
    }

    public Venue(@NotNull Console console) {
        id = nextId++;
        if (console.isInteractiveMode()) {
            System.out.println("Создаём объект типа \"Venue\":");
            System.out.println("Поле имя:");
        }
        setName(console.getString());

        if (console.isInteractiveMode())
            System.out.println("Поле capacity:");
        setCapacity(console.getInt(CAPACITY_MIN));

        if (console.isInteractiveMode())
            System.out.println("Поле type:");

        setType(console.getEnumConstant(VenueType.class, false));
        setAddress(new Address(console));

        if (console.isInteractiveMode())
            System.out.println("Создан объект: " + this);
    }

    // All gets
    public static long getNextAvailableId() {
        return nextId;
    }

    /**
     * @return true, если все поля заданы верно, иначе могут быть @exception или false
     */
    public boolean isExisting() {
        setName(name).setCapacity(capacity).setType(type).setAddress(address);
        address.isExisting();
        if (id < 1)
            throw new IllegalStateException();
        return true;
    }

    private void initial(String name, int capacity, VenueType type, Address address) {
        setName(name).setCapacity(capacity).setType(type).setAddress(address);
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
    public @NotNull String toString() {
        return "Venue{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", capacity=" + capacity +
                ", type=" + type +
                ", address=" + address +
                '}';
    }

    @Override
    public int compareTo(@NotNull Venue o) {
        if (equals(o))
            return 0;
        else
            return address.compareTo(o.getAddress());
    }

    public long getId() {
        return id;
    }

    public @Nullable Address getAddress() {
        return address;
    }

    public @NotNull Venue setAddress(@Nullable Address address) {
        if (address == null)
            throw new NullPointerException("Поле не может быть null");
        this.address = address;
        return this;
    }

    public int getCapacity() {
        return capacity;
    }

    public @NotNull Venue setCapacity(int capacity) {
        if (capacity < CAPACITY_MIN)
            throw new IllegalArgumentException("Значение поля capacity должно быть больше " + (CAPACITY_MIN - 1));
        this.capacity = capacity;
        return this;
    }

    //All sets

    public @Nullable String getName() {
        return name;
    }

    public @NotNull Venue setName(@Nullable String name) {
        if (name == null)
            throw new NullPointerException("Поле name не может быть null");
        if (name.equals(""))
            throw new IllegalArgumentException("name не может быть пустым");
        this.name = name;
        return this;
    }

    public @Nullable VenueType getType() {
        return type;
    }

    public @NotNull Venue setType(@Nullable VenueType type) {
        if (type == null)
            throw new NullPointerException("Поле не может быть null");
        this.type = type;
        return this;
    }

}
