package ru.ifmo.se.kastricyn.ticket;

import java.util.Objects;

public class Address {
    private String street; //Строка не может быть пустой, Поле может быть null

    public Address(String street) {
        if (street == null)
            throw new NullPointerException("Поле не может быть null");
        if (street.equals(""))
            throw new IllegalArgumentException("street не может быть пустым");
        this.street = street;
    }

    public String getStreet() {
        return street;
    }

    public Address setStreet(String street) {
        if (street == null)
            throw new NullPointerException("Поле не может быть null");
        if (street.equals(""))
            throw new IllegalArgumentException("street не может быть пустым");
        this.street = street;
        return this;
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
