package ru.ifmo.se.kastricyn;

import ru.ifmo.se.kastricyn.commands.*;
import ru.ifmo.se.kastricyn.utility.Console;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;
import java.util.stream.Stream;

/**
 * Исполнитель комманд. Класс реализует управление коммандами, доступными для пользователя.
 * Разные объекты этого класса могут по-разному исполнять комманды.
 */
public class CommandManager {
    private HashMap<String, AbstractCommand> commands;

    public final TicketCollection ticketCollection;
    public final Console console;

    /**
     * Создаёт менеджер коммандами.
     * @param ticketCollection коллекция с которой будут работать команды
     * @param console объект типа {@link Console} с помощью которого происходит взаимодействие с пользователем
     */
    public CommandManager(TicketCollection ticketCollection, Console console) {
        commands = new HashMap<>();
        this.ticketCollection = ticketCollection;
        this.console = console;
    }

    /**
     * Возвращает менеджер комманд поумолчанию
     * @param ticketCollection коллекция с которой будут работать команды
     * @param console объект типа {@link Console} с помощью которого происходит взаимодействие с пользователем
     */
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

    /**
     * Добавляет команду в менеджер команд, если её не было до этого
     * @param cmd команда
     */
    public void addIfAbsent(AbstractCommand cmd) {
        commands.putIfAbsent(cmd.getName(), cmd);
    }

    /**
     * Возвращает поток из команд в строковом представлении
     */
    public Stream<String> getCommandsToString() {
         return commands.values().stream().map(AbstractCommand::toString);
    }

    /**
     * Исполняет команду имя которой передано в первом аргументе
     * @param commandName имя команды
     * @param args аргументы команды в строковом представлении
     */
    public void execute(String commandName, String ... args) {
        AbstractCommand command = commands.get(commandName.toLowerCase());
        if (command == null) {
            System.out.println("Такой команды не существует. Для вызова справки введите: help");
            return;
        }
        command.execute(args);
    }

    public void run() {
        while (console.hasNext()) {
            String t = console.nextLine().trim();
            if (t.isEmpty())
                continue;
            String[] s = t.trim().split("\\s");
            execute(s[0], Arrays.copyOfRange(s, 1, s.length));
        }
    }
}
