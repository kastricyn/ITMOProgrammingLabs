package ru.ifmo.se.kastricyn.lab6.server.commandManager;

import ru.ifmo.se.kastricyn.lab6.lib.Command;
import ru.ifmo.se.kastricyn.lab6.lib.data.Ticket;
import ru.ifmo.se.kastricyn.lab6.lib.data.Venue;
import ru.ifmo.se.kastricyn.lab6.lib.utility.Console;
import ru.ifmo.se.kastricyn.lab6.server.TicketCollection;
import ru.ifmo.se.kastricyn.lab6.server.commands.*;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Управление комманд, которые вводятся с консоли
 */
public class ConsoleCommandManager extends CommandManager {
    private final Console console;

    public ConsoleCommandManager(TicketCollection ticketCollection, Console console) {
        super(ticketCollection);
        this.console = console;
    }

    /**
     * @param ticketCollection коллекция с которой будут работать команды
     * @param console          объект типа {@link Console} с помощью которого происходит взаимодействие с пользователем
     */
    public static ConsoleCommandManager getStandardsConsoleCommandManager(TicketCollection ticketCollection, Console console) {
        ConsoleCommandManager ccm = new ConsoleCommandManager(ticketCollection, console);
        ccm.addIfAbsent(new Add());
        ccm.addIfAbsent(new AddIfMax());
        ccm.addIfAbsent(new Clear());
        ccm.addIfAbsent(new ExecuteScript());
        ccm.addIfAbsent(new Exit());
        ccm.addIfAbsent(new FilterByVenue());
        ccm.addIfAbsent(new Head());
        ccm.addIfAbsent(new Help());
        ccm.addIfAbsent(new Info());
        ccm.addIfAbsent(new PrintAscending());
        ccm.addIfAbsent(new PrintFieldDescendingVenue());
        ccm.addIfAbsent(new RemoveById());
        ccm.addIfAbsent(new RemoveLower());
        ccm.addIfAbsent(new Save());
        ccm.addIfAbsent(new Show());
        ccm.addIfAbsent(new Update());
        return ccm;
    }


    /**
     * Исполняет команду имя которой передано в первом аргументе
     *
     * @param commandName имя команды
     * @param args        аргументы команды в строковом представлении
     */
    public void executeCommand(String commandName, String... args) {
        Command command = getCommand(commandName);
        if (command == null) {
            console.printlnErr("Такой команды не существует. Для вызова справки введите: help");
            return;
        }

        ArrayList<Object> arguments = new ArrayList<>();
        for (Class eClass : command.getArgumentTypes()) {
            if (eClass.isInstance(ticketCollection))
                arguments.add(ticketCollection);
            else if (eClass.equals(Ticket.class))
                arguments.add(new Ticket(console));
            else if (eClass.equals(Venue.class))
                arguments.add(new Venue(console));
            else if (eClass.equals(CommandManager.class))
                arguments.add(this);
        }
        command.setArguments(arguments);
        command.execute(args);
        console.println(command.getAnswer());
        command.clearArguments();
    }


    /**
     * Принимает команды и обрабатыввает их
     */
    public void run() {
        String t = console.nextLine().trim();
        if (t.isEmpty())
            return;
        String[] s = t.trim().split("\\s");
        executeCommand(s[0], Arrays.copyOfRange(s, 1, s.length));
    }
}
