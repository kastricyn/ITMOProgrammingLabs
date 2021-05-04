package ru.ifmo.se.kastricyn.lab6.server;


import ru.ifmo.se.kastricyn.lab6.lib.AbstractCommand;
import ru.ifmo.se.kastricyn.lab6.lib.utility.Director;
import ru.ifmo.se.kastricyn.lab6.server.commands.*;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;
import java.util.stream.Stream;

/**
 * Исполнитель команд. Класс реализует управление коммандами, доступными для пользователя.
 * Разные объекты этого класса могут по-разному исполнять комманды.
 */
public class CommandManager {
    private HashMap<String, AbstractCommand> commands;

    public final TicketCollection ticketCollection;
    public final Director director;

    /**
     * Создаёт менеджер коммандами.
     *
     * @param ticketCollection коллекция с которой будут работать команды
     * @param director          объект типа {@link Director} с помощью которого происходит взаимодействие с пользователем
     */
    public CommandManager(TicketCollection ticketCollection, Director director) {
        commands = new HashMap<>();
        this.ticketCollection = ticketCollection;
        this.director = director;
    }

    /**
     * Возвращает менеджер комманд поумолчанию
     *
     * @param ticketCollection коллекция с которой будут работать команды
     * @param director          объект типа {@link Director} с помощью которого происходит взаимодействие с пользователем
     */
    public static CommandManager getServerCommandManager(TicketCollection ticketCollection, Director director) {
        Scanner in = director.getIn();
        boolean shouldPrintHints = director.isInteractiveMode();

        CommandManager cm = new CommandManager(ticketCollection, director);
        cm.addIfAbsent(new Add(ticketCollection, in, shouldPrintHints));
        cm.addIfAbsent(new AddIfMax(ticketCollection, in, shouldPrintHints));
        cm.addIfAbsent(new Clear(ticketCollection));
        cm.addIfAbsent(new ExecuteScript(ticketCollection));
        cm.addIfAbsent(new Exit(ticketCollection, director));
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
        cm.addIfAbsent(new Update(ticketCollection, director));
        return cm;
    }

    /**
     * Возвращает менеджер комманд поумолчанию
     *
     * @param ticketCollection коллекция с которой будут работать команды
     * @param director          объект типа {@link Director} с помощью которого происходит взаимодействие с пользователем
     */
    public static CommandManager getClientCommandManager(TicketCollection ticketCollection, Director director){
        //todo
        return null;
    }

    /**
     * Добавляет команду в менеджер команд, если её не было до этого
     *
     * @param cmd команда
     */
    public void addIfAbsent(AbstractCommand cmd) {
        commands.putIfAbsent(cmd.getName(), cmd);
    }

    /**
     * Возвращает поток из команд в строковом представлении
     */
    public Stream<String> getCommandsAsString() {
        return commands.values().stream().map(AbstractCommand::toString);
    }

    /**
     * Исполняет команду имя которой передано в первом аргументе
     *
     * @param commandName имя команды
     * @param args        аргументы команды в строковом представлении
     */
    public void executeCommand(String commandName, String... args) {
        AbstractCommand command = getCommand(commandName);
        if (command == null) {
            System.out.println("Такой команды не существует. Для вызова справки введите: help");
            return;
        }
        command.
        command.execute(args);
    }

    public AbstractCommand getCommand(String commandName){
        return commands.get(commandName.toLowerCase());
    }

    /**
     * Ожидает команду от пользователя и запускает её исполнение
     */
    public void run() {
        String t = director.nextLine().trim();
        if (t.isEmpty())
            return;
        String[] s = t.trim().split("\\s");
        executeCommand(s[0], Arrays.copyOfRange(s, 1, s.length));
    }
}
