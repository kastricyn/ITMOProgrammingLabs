package ru.ifmo.se.kastricyn.lab6.lib;

import java.util.ArrayList;

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
     *
     * @return
     */
    ArrayList<Class> getArgumentTypes();

    /**
     * устанавливает аргументы для команды, порядок должен соответствовать {@see getArgumentTypes}
     *
     * @param args аргуменьты
     */
    Command setArguments(Object... args);

    /**
     * Удаляет аргументы, для корректного последующего вызова
     */
    void clearArguments();

    /**
     * возвращает true, если у команды указаны верные параметры, инчае false
     */
    boolean objectsArgsIsValidate();

    /**
     * Возврщает строку ответ на команду
     */
    String getAnswer();
}
