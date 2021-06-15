package ru.ifmo.se.kastricyn.lab7.server.commands;

import ru.ifmo.se.kastricyn.lab7.lib.AbstractCommand;
import ru.ifmo.se.kastricyn.lab7.lib.CommandManager;
import ru.ifmo.se.kastricyn.lab7.lib.utility.Console;

/**
 * Команда вывести справку по доступным командам
 */
public class Help extends ServerAbstractCommand {

    public Help() {
        super("help", "вывести справку по доступным командам");

        setNeedArgumentType(CommandManager.class);
    }

    @Override
    public void execute(String... args) {
        assert objArgs != null;
        CommandManager commandManager = objArgs.getCommandManager();
        boolean auth = objArgs.getUser() != null && objArgs.getTicketCollection().getDb().checkPassword(objArgs.getUser());

        answer = Console.getStringFromStream("Доступны следующие команды:",
                commandManager.getCommands().filter(x -> auth || !(x instanceof CommandWithAuth)).map(AbstractCommand::toString).sorted());

    }
}
