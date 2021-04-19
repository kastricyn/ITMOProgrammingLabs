package ru.ifmo.se.kastricyn.commands;

import ru.ifmo.se.kastricyn.CommandManager;

import java.util.stream.Collectors;

public class Help extends AbstractCommand {
    private CommandManager commandManager;

    public Help(CommandManager commandManager) {
        super("help", "help \n - вывести справку по доступным командам");
        this.commandManager = commandManager;
    }

    @Override
    public void execute(String... args) {
        //todo: close stream better than now
        commandManager.getCommandsToString().sorted().peek(System.out::println).collect(Collectors.toList());
    }
}
