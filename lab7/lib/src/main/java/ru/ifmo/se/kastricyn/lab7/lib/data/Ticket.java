package ru.ifmo.se.kastricyn.lab7.lib.data;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.ifmo.se.kastricyn.lab7.lib.LocalDateAdapter;
import ru.ifmo.se.kastricyn.lab7.lib.utility.Console;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

/**
 * Класс представляющий элемент коллекции
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class Ticket implements Comparable<Ticket>, Serializable {
    public static final int PRICE_MIN = 1;
    public static final double DISCOUNT_MIN = 0;
    public static final double DISCOUNT_MAX = 100;

    private static long nextId = 1;

    // быть уникальным, Значение этого поля должно генерироваться автоматически
    @XmlJavaTypeAdapter(value = LocalDateAdapter.class)
    private @NotNull final LocalDate creationDate; //Поле не может быть null, Значение этого поля должно генерироваться
    @XmlAttribute
    private @NotNull Long id; //Поле не может быть null, Значение поля должно быть больше 0, Значение этого поля должно
    // автоматически
    private @NotNull String name; //Поле не может быть null, Строка не может быть пустой
    private @NotNull Coordinates coordinates; //Поле не может быть null
    private @Nullable TicketType type; //Поле может быть null
    private @Nullable Integer price; //Поле может быть null, Значение поля должно быть больше 0
    private double discount; //Значение поля должно быть больше 0, Максимальное значение поля: 100
    private @NotNull Venue venue; //Поле не может быть null
    public Ticket(long id, String name, Coordinates coordinates, @Nullable LocalDate creationDate, Integer price,
                  double discount, TicketType type, Venue venue, long userId) {
        initial(name, coordinates, price, discount, type, venue);
        if (creationDate == null)
            throw new NullPointerException("Поле creationDate не может быть null");
        this.creationDate = creationDate;
        if (id < nextId)
            throw new IllegalArgumentException("Значение поля id должно быть больше id предыдущего объекта этого типа/минимально возможного значения ("
                    + (nextId - 1) + ")");
        this.id = id;
        this.userId = userId;
        nextId = id + 1;
    }
    private long userId;

    public Ticket(String name, Coordinates coordinates, Integer price, double discount, TicketType type, Venue venue) {
        initial(name, coordinates, price, discount, type, venue);
        id = 0l;
        creationDate = LocalDate.now();
    }

    public Ticket setId(Long id) {
        this.id = id;
        return this;
    }


    @SuppressWarnings("deprecation")
    public Ticket(@NotNull Console console) {
        id = 0l;
        creationDate = LocalDate.now();
        if (console.isInteractiveMode()) {
            console.printHints("Создаём объект типа \"Ticket\":");
            System.out.println("Поле имя:");
        }
        setName(console.getString());

        setCoordinates(new Coordinates(console));

        if (console.isInteractiveMode())
            System.out.println("Возвращаемся к объекту типа \"Ticket\":\n Поле price:");
        setPrice(console.getInt(PRICE_MIN, Integer.MAX_VALUE, true));

        if (console.isInteractiveMode())
            System.out.println("Поле discount:");

        setDiscount(console.getDouble(DISCOUNT_MIN, DISCOUNT_MAX));

        if (console.isInteractiveMode())
            System.out.println("Поле type:");

        setType(console.getEnumConstant(TicketType.class, true));

        setVenue(new Venue(console));

        if (console.isInteractiveMode()) {
            System.out.println("Возвращаемся к объекту типа \"Ticket\":\n создан объект: " + this);
        }

    }

    /**
     * Конструктор билета для верного задания автоматических параметров
     *
     * @param ticket билет получаемый на вход
     */
    public Ticket(@NotNull Ticket ticket) {
        initial(ticket.getName(), ticket.getCoordinates(), ticket.getPrice(), ticket.getDiscount(), ticket.getType(),
                new Venue(ticket.getVenue()));
        id = nextId++;
        creationDate = LocalDate.now();
    }

    public static long getNextAvailableId() {
        return nextId;
    }

    public long getUserId() {
        return userId;
    }

    public Ticket setUserId(long userId) {
        this.userId = userId;
        return this;
    }

    /**
     * @return true, если все поля заданы верно, иначе могут быть @exception
     */
    public boolean isExisting() {
        if (id < 1)
            throw new IllegalStateException();
        setName(name).setCoordinates(coordinates).setPrice(price).setDiscount(discount).setType(type).setVenue(venue);
        coordinates.isExisting();
        venue.isExisting();
        if (creationDate == null)
            throw new NullPointerException();
        return true;
    }

    private void initial(String name, Coordinates coordinates, Integer price, double discount, TicketType type, Venue venue) {
        setName(name).setCoordinates(coordinates).setPrice(price).setDiscount(discount).setType(type).setVenue(venue);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Ticket)) return false;
        Ticket ticket = (Ticket) o;
        return Double.compare(ticket.discount, discount) == 0 && name.equals(ticket.name) && coordinates.equals(ticket.coordinates) && creationDate.equals(ticket.creationDate) && price.equals(ticket.price) && type == ticket.type && venue.equals(ticket.venue);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, coordinates, creationDate, price, discount, type, venue);
    }

    @Override
    public int compareTo(@NotNull Ticket o) {
        return name.compareTo(o.getName());
    }

//All gets

    @Override
    public @NotNull String toString() {
        return "Ticket{" +
                "id=" + id +
                ", userId=" + userId +
                ", name='" + name + '\'' +
                ", coordinates=" + coordinates +
                ", creationDate=" + creationDate +
                ", price=" + price +
                ", discount=" + discount +
                ", type=" + type +
                ", venue=" + venue +
                '}';
    }

    public long getId() {
        return id;
    }

    public @NotNull String getName() {
        return name;
    }

    public @NotNull Ticket setName(@Nullable String name) {
        if (name == null)
            throw new NullPointerException("Поле name не может быть null");
        if (name.equals(""))
            throw new IllegalArgumentException("name не может быть пустым");
        this.name = name;
        return this;
    }

    public double getDiscount() {
        return discount;
    }

    public @NotNull Ticket setDiscount(double discount) {
        if (discount < DISCOUNT_MIN || discount > DISCOUNT_MAX)
            throw new IllegalArgumentException("Значение поля discount должно быть больше " + DISCOUNT_MIN + ", Максимальное значение поля: " + DISCOUNT_MAX);
        this.discount = discount;
        return this;
    }

    public @NotNull Coordinates getCoordinates() {
        return coordinates;
    }

    public @NotNull Ticket setCoordinates(@Nullable Coordinates coordinates) {
        if (coordinates == null)
            throw new NullPointerException("поле coordinates не может быть null");
        this.coordinates = coordinates;
        return this;
    }

    public @Nullable TicketType getType() {
        return type;
    }

    //All sets

    public @NotNull Ticket setType(TicketType type) {
        this.type = type;
        return this;
    }

    public @Nullable Integer getPrice() {
        return price;
    }

    public @NotNull Ticket setPrice(@Nullable Integer price) {
        if (price == null)
            this.price = null;
        else if (price < PRICE_MIN)
            throw new IllegalArgumentException("Значение поля price должно быть больше" + (PRICE_MIN - 1));
        this.price = price;
        return this;
    }

    public @NotNull LocalDate getCreationDate() {
        return creationDate;
    }

    public @NotNull Venue getVenue() {
        return venue;
    }

    public @NotNull Ticket setVenue(@Nullable Venue venue) {
        if (venue == null)
            throw new NullPointerException("поле venue не может быть null");
        this.venue = venue;
        return this;
    }
}

