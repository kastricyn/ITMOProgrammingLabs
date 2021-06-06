package ru.ifmo.se.kastricyn.lab7.server.commands;


import ru.ifmo.se.kastricyn.lab7.lib.User;
import ru.ifmo.se.kastricyn.lab7.server.TicketCollection;
import ru.ifmo.se.kastricyn.lab7.server.db.DBUserI;

import java.util.ArrayList;

/**
 * Команды регистрирует нового пользователя
 */
public class Register extends ServerAbstractCommand {
    public Register() {
        super("register", "зарегистрировать нового пользователя");
        ArrayList<Class> argsType = new ArrayList<>();
        setNeedArgumentType(TicketCollection.class, User.class);
    }

    /**
     * @param args аргументы команды
     */
    @Override
    public void execute(String... args) {
        assert objArgs != null;
        DBUserI db = objArgs.getTicketCollection().getDb();
        if (db.registerUser(objArgs.getUser()))
            answer = "Новый пользователь зарегистрирован";
        else if (db.getId(objArgs.getUser()) != -1)
            answer = "Пользователь с таким логином уже существует";
        else
            answer = "Регистация не удалась, повторите.";
    }
}
