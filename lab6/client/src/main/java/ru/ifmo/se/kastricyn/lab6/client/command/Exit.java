package ru.ifmo.se.kastricyn.lab6.client.command;

import ru.ifmo.se.kastricyn.lab6.client.ClientAbstractCommand;
import ru.ifmo.se.kastricyn.lab6.client.ClientCommandManager;

public class Exit extends ClientAbstractCommand {

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
        //todo будет ли проверка с отложенным буфером, для этого реализуем отдельный поток по передачи данных и
        // взаимодейтвию с пользователем
        ClientCommandManager ccm = objArgs.getCommandManager();
        ccm.setWorkable(false);

    }
}
