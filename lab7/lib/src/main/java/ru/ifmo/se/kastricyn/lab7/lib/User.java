package ru.ifmo.se.kastricyn.lab7.lib;

import org.jetbrains.annotations.NotNull;
import ru.ifmo.se.kastricyn.lab7.lib.utility.Console;


/**
 * Описывает пользователя системы: (id, name, password)
 */
public class User {
    private long id = 0;
    @NotNull
    private String name;
    private char[] password = "".toCharArray();
    public User(long id, @NotNull String name, String password) {
        this.name = name;
        this.password = password.toCharArray();
    }

    public User(Console console) {
        console.printHints("Поле name:");
        name = console.getString();
        console.printHints("Пароль (строка может быть пустой):");
        password = System.console().readPassword();
    }

    public long getId() {
        return id;
    }

    public @NotNull String getName() {
        return name;
    }

    public User setName(@NotNull String name) {
        this.name = name;
        return this;
    }

    public String getPassword() {
        return new String(password);
    }

}
