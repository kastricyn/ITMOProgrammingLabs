package ru.ifmo.se.kastricyn;

import ru.ifmo.se.kastricyn.commands.*;

import java.util.HashMap;
import java.util.Scanner;

public class CommandManager {
    private HashMap<String, AbstractCommand> commands;

    public final TicketCollection ticketCollection;
    public final Scanner in;
    public final boolean shouldPrintHints;

    public CommandManager(TicketCollection ticketCollection, Scanner in, boolean shouldPrintHints) {
        commands = new HashMap<>();
        this.ticketCollection = ticketCollection;
        this.in = in;
        this.shouldPrintHints = shouldPrintHints;
    }

    public static CommandManager createCommandManager(TicketCollection ticketCollection, Scanner in, boolean shouldPrintHints) {
        CommandManager cm = new CommandManager(ticketCollection, in, shouldPrintHints);
        cm.addIfAbsent(new Add(ticketCollection, in, shouldPrintHints));
        cm.addIfAbsent(new AddIfMax(ticketCollection, in, shouldPrintHints));
        cm.addIfAbsent(new Clear(ticketCollection));
        cm.addIfAbsent(new ExecuteScript(ticketCollection));
        cm.addIfAbsent(new Exit(ticketCollection, in));
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
        cm.addIfAbsent(new Update(ticketCollection, in, shouldPrintHints));
        return cm;
    }

    public void addIfAbsent(AbstractCommand cmd) {
        commands.putIfAbsent(cmd.getName(), cmd);
    }

    public HashMap<String, AbstractCommand> getCommands() {
        return (HashMap<String, AbstractCommand>) commands.clone();
    }

    public void execute(String commandNameWithArgs) {
        if (commandNameWithArgs == null || commandNameWithArgs.isEmpty())
            return;
        String[] r = commandNameWithArgs.trim().split("\\s", 2);
        AbstractCommand command = commands.get(r[0].toLowerCase());
        if (command == null) {
            System.out.println("Такой команды не существует. Для вызова справки введите: help");
            return;
        }
        command.execute(r.length > 1 ? r[1] : "");
    }

    public void run() {
        while (in.hasNext()) {
            execute(in.nextLine());
        }
    }
}
