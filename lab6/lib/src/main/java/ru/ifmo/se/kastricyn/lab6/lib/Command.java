package ru.ifmo.se.kastricyn.lab6.lib;

import java.util.ArrayList;

/**
 * Функциональный интерфейс комманды
 */
public interface Command {
    ArrayList<Class> getParamTypes();
    /**
     * @param objects параметры
     */
    Command setParams(ArrayList<?> objects);

    /**
     * Выполняет команду
     * @param args аргументы команды
     */
    void execute(String ... args);

    /**
     * @return - строку ответ на команду
     */
    String getAnswer();
}
