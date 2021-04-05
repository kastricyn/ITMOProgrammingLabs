package ru.ifmo.se.kastricyn;

import ru.ifmo.se.kastricyn.commands.*;
import ru.ifmo.se.kastricyn.utility.Console;

import java.util.HashMap;
import java.util.Scanner;

public class CommandManager {
    private HashMap<String, AbstractCommand> commands;

    public final TicketCollection ticketCollection;
    public final Console console;

    public CommandManager(TicketCollection ticketCollection, Console console) {
        commands = new HashMap<>();
        this.ticketCollection = ticketCollection;
        this.console = console;
    }

    public static CommandManager createCommandManager(TicketCollection ticketCollection, Console console) {
        Scanner in = console.getIn();
        boolean shouldPrintHints = console.isInteractiveMode();

        CommandManager cm = new CommandManager(ticketCollection, console);
        cm.addIfAbsent(new Add(ticketCollection, in, shouldPrintHints));
        cm.addIfAbsent(new AddIfMax(ticketCollection, in, shouldPrintHints));
        cm.addIfAbsent(new Clear(ticketCollection));
        cm.addIfAbsent(new ExecuteScript(ticketCollection));
        cm.addIfAbsent(new Exit(ticketCollection, console));
        cm.addIfAbsent(new FilterByVenue(ticketCollection, in, shouldPrintHints));
        cm.addIfAbsent(new Head(ticketCollection));
        cm.addIfAbsent(new Help(cm));
        cm.addIfAbsent(new Info(ticketCollection));
        cm.addIfAbsent(new PrintAscending(ticketCollection));
        cm.addIfAbsent(new PrintFieldDescendingVenue(ticketCollection));
        cm.addIfAbsent(new RemoveById(ticketCollection));
        cm.addIfAbsent(new RemoveLower(ticketCollection, in, shouldPrintHints));
        cm.addIfAbsent(new Save(ticketCollection));
        cm.addIfAbsent(new Show(ticketCollection));
        cm.addIfAbsent(new Update(ticketCollection, console));
        return cm;
    }

    public void addIfAbsent(AbstractCommand cmd) {
        commands.putIfAbsent(cmd.getName(), cmd);
    }

    public HashMap<String, AbstractCommand> getCommands() {
        return (HashMap<String, AbstractCommand>) commands.clone();
    }

    public void execute(String commandNameWithArgs) {
        String[] r = commandNameWithArgs.trim().split("\\s", 2);
        AbstractCommand command = commands.get(r[0].toLowerCase());
        if (command == null) {
            System.out.println("Такой команды не существует. Для вызова справки введите: help");
            return;
        }
        command.execute(r.length > 1 ? r[1] : "");
    }

    public void run() {
        while (console.hasNext()) {
            String t = console.nextLine().trim();
            if (t.isEmpty())
                continue;
            execute(t);
        }
    }
}
