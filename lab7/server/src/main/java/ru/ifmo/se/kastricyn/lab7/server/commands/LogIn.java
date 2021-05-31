package ru.ifmo.se.kastricyn.lab7.server.commands;

import ru.ifmo.se.kastricyn.lab7.lib.User;
import ru.ifmo.se.kastricyn.lab7.server.TicketCollection;
import ru.ifmo.se.kastricyn.lab7.server.db.DBUserI;

public class LogIn extends ServerAbstractCommand {
    public LogIn() {
        super("log_in", "войти");
        setNeedArgumentType(TicketCollection.class, User.class);
    }

    /**
     * @param args аргументы команды
     */
    @Override
    public void execute(String... args) {
        assert objArgs != null;
        DBUserI db = objArgs.getTicketCollection().getDb();
        if (db.checkPassword(objArgs.getUser()))
            answer = "Вы авторизованы как " + objArgs.getUser().getName() + ", ваш id: " + db.getId(objArgs.getUser());
        else
            answer = "Логин или пароль не верны";
    }
}
