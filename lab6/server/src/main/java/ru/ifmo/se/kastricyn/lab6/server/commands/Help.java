package ru.ifmo.se.kastricyn.lab6.server.commands;

import ru.ifmo.se.kastricyn.lab6.lib.AbstractCommand;
import ru.ifmo.se.kastricyn.lab6.lib.utility.Console;
import ru.ifmo.se.kastricyn.lab6.server.CommandManager;

/**
 * Команда вывести справку по доступным командам
 */
public class Help extends AbstractCommand {

    public Help() {
        super("help", "вывести справку по доступным командам");

        setArgumentTypes(CommandManager.class);
    }

    @Override
    public void execute(String... args) {
        CommandManager commandManager = (CommandManager) this.args.get(0);

        answer = Console.getStringFromStream("Доступны следующие команды:",
                commandManager.getCommandsAsString().sorted());
    }
}
