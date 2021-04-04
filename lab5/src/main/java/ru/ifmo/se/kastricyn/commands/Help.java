package ru.ifmo.se.kastricyn.commands;

import ru.ifmo.se.kastricyn.CommandManager;

import java.util.*;

public class Help extends AbstractCommand {
    private CommandManager commandManager;

    public Help(CommandManager commandManager) {
        super("help", "help \n - вывести справку по доступным командам");
        this.commandManager = commandManager;
    }

    @Override
    public void execute(String... args) {
        LinkedList<AbstractCommand> commands = new LinkedList<>(commandManager.getCommands().values());
        Collections.sort(commands, Comparator.comparing(AbstractCommand::getName));
        commands.forEach((v) -> System.out.println(v.getDescription()));
    }
}
