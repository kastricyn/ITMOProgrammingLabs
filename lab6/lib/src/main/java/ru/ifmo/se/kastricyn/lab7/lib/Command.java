package ru.ifmo.se.kastricyn.lab7.lib;

import java.util.Set;

/**
 * Функциональный интерфейс комманды
 */
public interface Command {
    /**
     * Выполняет команду
     *
     * @param args аргументы команды
     */
    void execute(String... args);

    /**
     * возвращает типы нестроковых необходимых аргументов команды
     */
    Set<Class> getArgumentTypes();

    /**
     * устанавливает аргументы для команды
     *
     * @param args объект с аргументами
     */
    Command setArguments(CommandArgument args);

    /**
     * Удаляет аргументы, для корректного последующего вызова
     */
    void clearArguments();

    /**
     * возвращает true, если у команды указаны верные нестроковые аргументы, инчае false
     */
    boolean objectsArgsIsValidate();

    /**
     * Возврщает строку - результат команды
     */
    String getAnswer();
}
