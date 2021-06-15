package ru.ifmo.se.kastricyn.lab7.server.commands;


import ru.ifmo.se.kastricyn.lab7.server.db.DBUserI;

public abstract class CommandWithAuth extends ServerAbstractCommand {
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

    /**
     * Авторизует пользователя, устанавливает в objArgs.user = user (id, name)
     *
     * @return true, если переданный пользователь авторизован; false если пользователь не найден или пароль не верен
     */
    protected boolean auth() {
        assert objArgs != null;
        if (objArgs.getUser() == null) {
            answer = DBUserI.needAuth;
            return false;
        }
        objArgs.setUser(objArgs.getTicketCollection().getDb().auth(objArgs.getUser()));
        if (objArgs.getUser() == null) {
            answer = DBUserI.needAuth;
            return false;
        } else return true;
    }

}
