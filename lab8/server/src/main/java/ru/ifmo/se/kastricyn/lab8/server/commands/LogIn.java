package ru.ifmo.se.kastricyn.lab8.server.commands;

import ru.ifmo.se.kastricyn.lab8.lib.User;
import ru.ifmo.se.kastricyn.lab8.server.TicketCollection;
import ru.ifmo.se.kastricyn.lab8.server.db.DBUserI;

public class LogIn extends ServerAbstractCommand {
    public LogIn() {
        super("log_in", "войти/сменить пользователя");
        setNeedArgumentType(TicketCollection.class, User.class);
    }

    /**
     * @param args аргументы команды
     */
    @Override
    public synchronized void execute(String... args) {

        assert objArgs != null;
        DBUserI db = objArgs.getTicketCollection().getDb();
        User user = db.auth(objArgs.getUser());
        if (user != null)
            answer = "Вы авторизованы как " + user.getName() + ", ваш id: " + user.getId();
        else
            answer = "Логин или пароль не верны";
    }
}
