package ru.ifmo.se.kastricyn.lab5;

/**
 * Функциональный интерфейс комманды
 */
@FunctionalInterface
public interface Command {
    /**
     * Выполняет команду
     * @param args аргументы команды
     */
    void execute(String ... args);
}
