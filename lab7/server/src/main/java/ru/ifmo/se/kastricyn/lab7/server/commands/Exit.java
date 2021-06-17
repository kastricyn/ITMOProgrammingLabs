package ru.ifmo.se.kastricyn.lab7.server.commands;

import ru.ifmo.se.kastricyn.lab7.lib.CommandManager;
import ru.ifmo.se.kastricyn.lab7.server.TicketCollection;

/**
 * Команда выйти из программы
 */
public class Exit extends ServerAbstractCommand {

    public Exit() {
        super("exit", "завершить программу");

        setNeedArgumentType(CommandManager.class, TicketCollection.class);
    }

    //TODO: общая функция для всех выходов из программы

    @Override
    public void execute(String... args) {
        assert objArgs != null;
        CommandManager cm = objArgs.getCommandManager();
        cm.setWorkable(false);
    }

}
