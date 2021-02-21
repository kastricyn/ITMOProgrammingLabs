package ticket;

import java.util.Objects;

public class Address {
    private String street; //Строка не может быть пустой, Поле может быть null

    public Address(String street) {
        if (street != null && street.equals(""))
            throw new IllegalArgumentException("Строка не может быть пустой, Поле может быть null");
        this.street = street;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Address)) return false;
        Address address = (Address) o;
        return Objects.equals(street, address.street);
    }

    @Override
    public int hashCode() {
        return Objects.hash(street);
    }

    @Override
    public String toString() {
        return "Address{" +
                "street='" + street + '\'' +
                '}';
    }
}
