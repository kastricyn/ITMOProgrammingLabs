package ru.ifmo.se.kastricyn;

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
