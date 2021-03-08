package ru.ifmo.se.kastricyn.commands;

import ru.ifmo.se.kastricyn.CommandManager;

public class Help extends AbstractCommand {
    private CommandManager commandManager;
    public Help(CommandManager commandManager) {
        super("help","help \n - вывести справку по доступным командам");
        this.commandManager = commandManager;
    }

    @Override
    public void execute(String ... args) {
        commandManager.getCommands().forEach((k, v) -> {
            System.out.println(v.getDescription());
        });
    }
}
