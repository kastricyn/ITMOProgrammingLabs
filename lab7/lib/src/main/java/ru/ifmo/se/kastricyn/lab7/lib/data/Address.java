package ru.ifmo.se.kastricyn.lab7.lib.data;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.ifmo.se.kastricyn.lab7.lib.utility.Console;

import java.io.Serializable;
import java.util.Objects;

/**
 * Надо для {@link Venue}
 */
public class Address implements Comparable<Address>, Serializable {
    private volatile @Nullable String street; //Строка не может быть пустой, Поле может быть null


    /**
     *
     * @return true, если все поля заданы верно, иначе могут быть @exception
     */
    public synchronized boolean isExisting(){
        setStreet(street);
        return true;
    }

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
    public Address(@NotNull Console console) {
        if (console.isInteractiveMode()) {
            System.out.println("Создаём объект типа \"Address\":");
            System.out.println("Введите пожалуйста улицу:");
        }
        street = console.getString(true);
        if (console.isInteractiveMode())
            System.out.println("Создан объект: " + this);
    }

    /**
     * сравниваются по полю street типа String
     * @param o объект сравнения
     * @return true, если равны, иначе false
     */
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
    public @NotNull String toString() {
        return "Address{" +
                "street='" + street + '\'' +
                '}';
    }

    /**
     *
     * @return поле street
     */
    public @Nullable String getStreet() {
        return street;
    }

    /**
     * Устанавливает sreet
     * @param street
     * @return обновлённый объект типа Address
     * @exception IllegalArgumentException если street.isEmpty() возвращает true
     */
    public @NotNull Address setStreet(@Nullable String street) {
        if (street != null && street.isEmpty())
            throw new IllegalArgumentException();
        this.street = street;
        return this;
    }

    /**
     * Сравниваются как строки, содержащие street (null всегда меньше, оба null - равны)
     * @param o объект сравнения
     * @return
     */
    @Override
    public int compareTo(@NotNull Address o) {
        if(o.getStreet()==null && street==null)
            return 0;
        if (o.getStreet() == null)
            return 1;
        if (street == null) return -1;
        return street.compareTo(o.getStreet());
    }
}
