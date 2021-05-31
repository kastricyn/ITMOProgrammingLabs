package ru.ifmo.se.kastricyn.lab7.server.commands;

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
        CommandManager commandManager = objArgs.getCommandManager();

        answer = Console.getStringFromStream("Доступны следующие команды:",
                commandManager.getCommandsAsString().sorted());

    }
}
