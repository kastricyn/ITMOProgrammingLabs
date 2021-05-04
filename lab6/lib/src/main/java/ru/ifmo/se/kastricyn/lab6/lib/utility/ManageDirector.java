package ru.ifmo.se.kastricyn.lab6.lib.utility;

import ru.ifmo.se.kastricyn.lab6.lib.Command;

import java.util.ArrayList;

public class ManageDirector {

    /**
     * Устанавливает параметры для переданной команды
     * @param command команда которой надо установить параметры
     * @return true если установлено иначе false
     */
    public boolean setParams(Command command){
        ArrayList<Object> params = new ArrayList<>(command.getArgumentTypes().size());
        //todo get Params в зависимости от консоли и команды или чего-то ещё
        command.setArguments(params);
        return true;
    }
}
