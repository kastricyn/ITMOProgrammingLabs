package ticket;

public class Address {
    private String street; //Строка не может быть пустой, Поле может быть null

    public Address(String street) {
        if (street != null && street.equals(""))
            throw new IllegalArgumentException("Строка не может быть пустой, Поле может быть null");
        this.street = street;
    }
}
