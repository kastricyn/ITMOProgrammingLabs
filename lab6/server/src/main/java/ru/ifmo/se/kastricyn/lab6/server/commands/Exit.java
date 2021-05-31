package ru.ifmo.se.kastricyn.lab6.server.commands;

import ru.ifmo.se.kastricyn.lab6.lib.CommandManager;
import ru.ifmo.se.kastricyn.lab6.server.ServerAbstractCommand;
import ru.ifmo.se.kastricyn.lab6.server.ServerCommandArgument;
import ru.ifmo.se.kastricyn.lab6.server.TicketCollection;

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
        CommandManager cm = objArgs.getCommandManager();
        Save s = new Save();
        s.setArguments(new ServerCommandArgument().setTicketCollection(objArgs.getTicketCollection()));
        s.execute();
        answer = s.getAnswer();
        cm.setWorkable(false);
    }

}
