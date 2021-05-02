package ru.ifmo.se.kastricyn.lab6.lib;

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
