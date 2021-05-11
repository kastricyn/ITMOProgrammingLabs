package ru.ifmo.se.kastricyn.lab6.server.commands;

import ru.ifmo.se.kastricyn.lab6.lib.AbstractCommand;
import ru.ifmo.se.kastricyn.lab6.lib.AbstractCommandManager;
import ru.ifmo.se.kastricyn.lab6.lib.utility.Console;

/**
 * Команда вывести справку по доступным командам
 */
public class Help extends AbstractCommand {

    public Help() {
        super("help", "вывести справку по доступным командам");

        setArgumentTypes(AbstractCommandManager.class);
    }

    @Override
    public void execute(String... args) {
        AbstractCommandManager abstractCommandManager = (AbstractCommandManager) this.args.get(0);

        answer = Console.getStringFromStream("Доступны следующие команды:",
                abstractCommandManager.getCommandsAsString().sorted());

    }
}
