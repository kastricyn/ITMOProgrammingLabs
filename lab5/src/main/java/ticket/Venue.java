package ticket;

public class Venue {
    private static long nextID = 1;
    private long id; //Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
    private String name; //Поле не может быть null, Строка не может быть пустой
    private int capacity; //Значение поля должно быть больше 0
    private VenueType type; //Поле не может быть null
    private Address address; //Поле не может быть null

    {
        if (name == null || type == null || address == null)
            throw new NullPointerException("Ни одно поле не может быть равно null");
        if (capacity <= 0)
            throw new IllegalArgumentException("Значение поля capacity должно быть больше 0");
    }

    public Venue(String name, int capacity, VenueType type, Address address) {
        id = nextID++;
        this.name = name;
        this.capacity = capacity;
        this.type = type;
        this.address = address;
    }

    public Venue(long id, String name, int capacity, VenueType type, Address address) {
        this.id = id;
        nextID = id + 1;
        this.name = name;
        this.capacity = capacity;
        this.type = type;
        this.address = address;
    }

}
