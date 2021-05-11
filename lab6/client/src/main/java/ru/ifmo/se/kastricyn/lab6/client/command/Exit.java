package ru.ifmo.se.kastricyn.lab6.client.command;

import ru.ifmo.se.kastricyn.lab6.client.ClientCommandManager;
import ru.ifmo.se.kastricyn.lab6.lib.AbstractCommand;

public class Exit extends AbstractCommand {

    public Exit() {
        super("exit", "завершение работы программы");
        setArgumentTypes(ClientCommandManager.class);

    }

    /**
     * @param args аргументы команды
     * @throws IndexOutOfBoundsException if paramsIsValidate()!=true
     */
    @Override
    public void execute(String... args) {
        //todo будет ли проверка с отложенным буфером, для этого реализуем отдельный поток по передачи данных и
        // взаимодейтвию с пользователем
        ClientCommandManager ccm = (ClientCommandManager) getArguments().get(0);
        ccm.exit();

    }
}
