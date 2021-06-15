package ru.ifmo.se.kastricyn.lab7.server.commands;


import ru.ifmo.se.kastricyn.lab7.lib.User;
import ru.ifmo.se.kastricyn.lab7.server.db.DBUserI;

public abstract class CommandWithAuth extends ServerAbstractCommand {
    protected User user;

    /**
     * конструктор класса наследника, принимает на вход параметры, необходимые для реализации конкретной команды
     * вызвается из конструктора класса наследника
     *
     * @param name        имя команды
     * @param description описание команды
     */
    public CommandWithAuth(String name, String description) {
        super(name, description);
    }

    protected boolean auth() {
        assert objArgs != null;
        if (objArgs.getUser() == null || objArgs.getTicketCollection().getDb().auth(objArgs.getUser()) == null) {
            answer = DBUserI.needAuth;
            return false;
        } else return true;
    }

}
