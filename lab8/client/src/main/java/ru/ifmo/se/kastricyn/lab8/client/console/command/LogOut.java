package ru.ifmo.se.kastricyn.lab8.client.console.command;

import ru.ifmo.se.kastricyn.lab8.client.console.ClientAbstractCommand;
import ru.ifmo.se.kastricyn.lab8.client.console.ClientCommandManager;

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
