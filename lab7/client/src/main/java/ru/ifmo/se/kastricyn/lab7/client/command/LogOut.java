package ru.ifmo.se.kastricyn.lab7.client.command;

import ru.ifmo.se.kastricyn.lab7.client.ClientAbstractCommand;
import ru.ifmo.se.kastricyn.lab7.client.ClientCommandManager;

public class LogOut extends ClientAbstractCommand {
    public LogOut() {
        super("log_out", "деавторизоваться");
        setNeedArgumentType(ClientCommandManager.class);
    }

    /**
     * @param args аргументы команды
     */
    @Override
    public void execute(String... args) {
        assert objArgs != null;
        ClientCommandManager ccm = objArgs.getCommandManager();
        if (ccm.getUser() == null)
            answer = "Было бы неплохо сначала войти";
        else {
            answer = "Вы вышли.";
            ccm.setUser(null);
        }
    }
}
