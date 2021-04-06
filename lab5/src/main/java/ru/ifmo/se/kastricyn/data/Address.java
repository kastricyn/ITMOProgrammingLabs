package ru.ifmo.se.kastricyn.data;

import ru.ifmo.se.kastricyn.utility.Console;

import java.util.Objects;

public class Address implements Comparable<Address> {
    private String street; //Строка не может быть пустой, Поле может быть null
    private Address(){} //for working JAXB

    /**
     * Конструктор Address
     *
     * @param street строка содержащая улицу
     */
    public Address(String street) {
        setStreet(street);
    }

    /**
     * Конструктор Address
     *
     * @param console объект типа Console, методами которго будут получены данные от пользователя с учётом ограничений
     */
    public Address(Console console) {
        if (console.isInteractiveMode()) {
            System.out.println("Создаём объект типа \"Address\":");
            System.out.println("Введите пожалуйста улицу:");
        }
        street = console.getString(true);
        if (console.isInteractiveMode())
            System.out.println("Создан объект: " + this);
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

    public String getStreet() {
        return street;
    }

    public Address setStreet(String street) {
        if (street != null && street.isEmpty())
            throw new IllegalArgumentException();
        this.street = street;
        return this;
    }

    @Override
    public int compareTo(Address o) {
        return street.compareTo(o.getStreet());
    }
}
