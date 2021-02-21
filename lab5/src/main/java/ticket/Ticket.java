package ticket;


import java.time.LocalDate;
import java.util.Objects;

public class Ticket implements Comparable<Ticket> {
    public static final int PRICE_MIN = 1;
    public static final double DISCOUNT_MIN_HARD = 0;
    public static final double DISCOUNT_MAX = 100;
    private static final long ID_MIN = 1;

    static private long nextId = ID_MIN;

    private Long id; //Поле не может быть null, Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
    private String name; //Поле не может быть null, Строка не может быть пустой
    private Coordinates coordinates; //Поле не может быть null
    private LocalDate creationDate; //Поле не может быть null, Значение этого поля должно генерироваться автоматически
    private Integer price; //Поле может быть null, Значение поля должно быть больше 0
    private double discount; //Значение поля должно быть больше 0, Максимальное значение поля: 100
    private TicketType type; //Поле может быть null
    private Venue venue; //Поле не может быть null

    private void initial(String name, Coordinates coordinates, int price, double discount, TicketType type, Venue venue) {
        if (name == null || coordinates == null || type == null || venue == null)
            throw new NullPointerException("Ни одно поле не может быть равно null");
        if (name.equals(""))
            throw new IllegalArgumentException("поле name не может быть пустым");
        if (price < PRICE_MIN)
            throw new IllegalArgumentException("Значение поля price должно быть больше" + (PRICE_MIN - 1));
        if (discount <= DISCOUNT_MIN_HARD || discount > DISCOUNT_MAX)
            throw new IllegalArgumentException("Значение поля discount должно быть больше " + DISCOUNT_MIN_HARD + ", Максимальное значение поля: " + DISCOUNT_MAX);

        this.name = name;
        this.coordinates = coordinates;
        this.price = price;
        this.discount = discount;
        this.type = type;
        this.venue = venue;
    }

    public Ticket(String name, Coordinates coordinates, int price, double discount, TicketType type, Venue venue) {
        initial(name, coordinates, price, discount, type, venue);
        id = nextId++;
        creationDate = LocalDate.now();
    }

    public Ticket(long id, String name, Coordinates coordinates, LocalDate creationDate, int price, double discount, TicketType type, Venue venue) {
        initial(name, coordinates, price, discount, type, venue);
        if (creationDate == null)
            throw new NullPointerException("Поле creationDate не может быть null");
        this.creationDate = creationDate;
        if (id < ID_MIN)
            throw new IllegalArgumentException("Значение поля id должно быть больше " + (ID_MIN - 1));
        if (id < nextId)
            throw new IllegalArgumentException("Значение поля id должно быть больше id предыдущего объекта этого типа ("
                    + (nextId - 1) + ")");
        this.id = id;
        nextId = id + 1;
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
    public int compareTo(Ticket o) {
        if(equals(o))
            return 0;
        else
            return (int) (venue.compareTo(o.getVenue())*(100-discount));
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", coordinates=" + coordinates +
                ", creationDate=" + creationDate +
                ", price=" + price +
                ", discount=" + discount +
                ", type=" + type +
                ", venue=" + venue +
                '}';
    }

    public Venue getVenue() {
        return venue;
    }
}

