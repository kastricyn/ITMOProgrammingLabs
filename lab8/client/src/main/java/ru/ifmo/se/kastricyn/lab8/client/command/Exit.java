package ru.ifmo.se.kastricyn.lab8.client.command;

import ru.ifmo.se.kastricyn.lab8.client.ClientAbstractCommand;
import ru.ifmo.se.kastricyn.lab8.client.ClientCommandManager;
import ru.ifmo.se.kastricyn.lab8.lib.utility.NotNeedAuth;

public class Exit extends ClientAbstractCommand implements NotNeedAuth {

    public Exit() {
        super("exit", "завершение работы программы");
        setNeedArgumentType(ClientCommandManager.class);
    }

    /**
     * @param args аргументы команды
     * @throws IndexOutOfBoundsException if paramsIsValidate()!=true
     */
    @Override
    public void execute(String... args) {
        assert objArgs != null;
        ClientCommandManager ccm = objArgs.getCommandManager();
        ccm.setWorkable(false);
    }
}
