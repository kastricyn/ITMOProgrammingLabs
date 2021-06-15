package ru.ifmo.se.kastricyn.lab7.lib;

import org.jetbrains.annotations.NotNull;
import ru.ifmo.se.kastricyn.lab7.lib.utility.Console;

import java.io.Serializable;
import java.util.Arrays;


/**
 * Описывает пользователя системы: (id, name, password)
 */
public class User implements Serializable {
    private long id = -1;
    @NotNull
    private String name;
    private char[] password;

    public User(long id, @NotNull String name) {
        this.id = id;
        this.name = name;
    }

    public User(@NotNull String name, char[] password) {
        this.name = name;
        this.password = password;
    }

    public User(long id, @NotNull String name, @NotNull String password) {
        this.id = id;
        this.name = name;
        this.password = password.toCharArray();
    }

    public User(@NotNull Console console) {
        console.printHints("Поле login:");
        name = console.getString();
        console.printHints("Пароль (строка может быть пустой):");
        password = console.readPassword();
    }

    public long getId() {
        return id;
    }

    public User setId(long id) {
        this.id = id;
        return this;
    }

    public @NotNull String getName() {
        return name;
    }

    public @NotNull User setName(@NotNull String name) {
        this.name = name;
        return this;
    }

    public char[] getPassword() {
        return password;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", password=" + Arrays.toString(password) +
                '}';
    }
}
