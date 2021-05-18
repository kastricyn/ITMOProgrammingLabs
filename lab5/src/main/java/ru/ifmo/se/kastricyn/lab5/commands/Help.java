package ru.ifmo.se.kastricyn.lab5.commands;

import ru.ifmo.se.kastricyn.lab5.CommandManager;

/**
 * Команда вывести справку по доступным командам
 */
public class Help extends AbstractCommand {
    private CommandManager commandManager;

    public Help(CommandManager commandManager) {
        super("help", "help \n - вывести справку по доступным командам");
        this.commandManager = commandManager;
    }

    @Override
    public void execute(String... args) {
        //todo: close stream better than now
        commandManager.getCommandsToString().sorted().forEachOrdered(System.out::print);
        return;
    }
}
