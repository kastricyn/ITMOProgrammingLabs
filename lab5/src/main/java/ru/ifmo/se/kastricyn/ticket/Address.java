package ru.ifmo.se.kastricyn.ticket;

import ru.ifmo.se.kastricyn.TryAgain;

import java.util.Objects;
import java.util.Scanner;

public class Address implements Comparable<Address>{
    private String street; //Строка не может быть пустой, Поле может быть null

    public Address(String street) {
        setStreet(street);
    }

    private Address() {
    }

    public static Address getAddress(Scanner in, boolean shouldPrintHints) {
        Address address = new Address();
        if (shouldPrintHints) {
            System.out.println("Создаём объект типа \"Address\":");
            System.out.println("Введите пожалуйста улицу:");
        }
        while (true)
            try {
                address.setStreet(in.nextLine());
                break;
            } catch (RuntimeException e) {
                TryAgain.printErrors(shouldPrintHints, e);
            }

        if (shouldPrintHints)
            System.out.println("Создан объект: " + address);

        return address;
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
        if (street == null)
            throw new NullPointerException("Поле не может быть null");
        if (street.equals(""))
            throw new IllegalArgumentException("street не может быть пустым");
        this.street = street;
        return this;
    }

    @Override
    public int compareTo(Address o) {
        return street.compareTo(o.getStreet());
    }
}
