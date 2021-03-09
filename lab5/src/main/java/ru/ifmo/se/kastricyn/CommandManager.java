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

    public static CommandManager getStandartCommandManager(TicketCollection ticketCollection, Scanner in, boolean shouldPrintHints) {
        CommandManager cm = new CommandManager(ticketCollection, in, shouldPrintHints);
        cm.addIfAbsent(new Add(ticketCollection, in, shouldPrintHints));
        cm.addIfAbsent(new AddIfMax(ticketCollection, in, shouldPrintHints));
        cm.addIfAbsent(new Clear(ticketCollection));
        cm.addIfAbsent(new ExecuteScript(ticketCollection));
        cm.addIfAbsent(new Exit());
        cm.addIfAbsent(new FilterByVenue(ticketCollection, in, shouldPrintHints));
        cm.addIfAbsent(new Head(ticketCollection));
        cm.addIfAbsent(new Help(cm));
        //todo: Info
        cm.addIfAbsent(new PrintAscending(ticketCollection));
        cm.addIfAbsent(new PrintFieldDescendingVenue(ticketCollection));
        cm.addIfAbsent(new RemoveById(ticketCollection));
        cm.addIfAbsent(new RemoveLower(ticketCollection, in, shouldPrintHints));
        //todo: save
        cm.addIfAbsent(new Show(ticketCollection));
        cm.addIfAbsent(new Update(ticketCollection, in, shouldPrintHints));
        return cm;
    }

    public AbstractCommand addIfAbsent(AbstractCommand cmd) {
        return commands.putIfAbsent(cmd.getName(), cmd);
    }

    public HashMap<String, AbstractCommand> getCommands() {
        return (HashMap<String, AbstractCommand>) commands.clone();
    }

    public void execute(String commandNameWithArgs) {
        if (commandNameWithArgs == null)
            throw new NullPointerException();
        String[] r = commandNameWithArgs.split("\\s", 2);
        AbstractCommand command = commands.get(r[0].toLowerCase());
        if (command == null)
            throw new IllegalArgumentException("Такой команды не существует");
        command.execute(r[1]);
    }

    public void run() {
        while (in.hasNext()) {
            execute(in.nextLine());
        }
    }
}
