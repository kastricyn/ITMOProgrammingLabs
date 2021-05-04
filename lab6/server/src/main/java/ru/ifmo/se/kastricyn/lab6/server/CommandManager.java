package ru.ifmo.se.kastricyn.lab6.server;


import ru.ifmo.se.kastricyn.lab6.lib.AbstractCommand;
import ru.ifmo.se.kastricyn.lab6.lib.Command;
import ru.ifmo.se.kastricyn.lab6.lib.utility.Console;
import ru.ifmo.se.kastricyn.lab6.server.commands.*;

import java.util.Arrays;
import java.util.HashMap;
import java.util.stream.Stream;

/**
 * Исполнитель команд. Класс реализует управление коммандами, доступными для пользователя.
 * Разные объекты этого класса могут по-разному исполнять комманды.
 */
public class CommandManager {
    private HashMap<String, AbstractCommand> commands;

    public final TicketCollection ticketCollection;
    public final Console console;

    /**
     * Создаёт менеджер коммандами.
     *
     * @param ticketCollection коллекция с которой будут работать команды
     * @param console          объект типа {@link Console} с помощью которого происходит взаимодействие с пользователем
     */
    public CommandManager(TicketCollection ticketCollection, Console console) {
        commands = new HashMap<>();
        this.ticketCollection = ticketCollection;
        this.console = console;
    }

    /**
     * Возвращает менеджер комманд поумолчанию
     *
     * @param ticketCollection коллекция с которой будут работать команды
     * @param console          объект типа {@link Console} с помощью которого происходит взаимодействие с пользователем
     */
    public static CommandManager getServerCommandManager(TicketCollection ticketCollection, Console console) {

        CommandManager cm = new CommandManager(ticketCollection, console);
        cm.addIfAbsent(new Add());
        cm.addIfAbsent(new AddIfMax());
        cm.addIfAbsent(new Clear());
        cm.addIfAbsent(new ExecuteScript());
        cm.addIfAbsent(new Exit());
        cm.addIfAbsent(new FilterByVenue());
        cm.addIfAbsent(new Head());
        cm.addIfAbsent(new Help());
        cm.addIfAbsent(new Info());
        cm.addIfAbsent(new PrintAscending());
        cm.addIfAbsent(new PrintFieldDescendingVenue());
        cm.addIfAbsent(new RemoveById());
        cm.addIfAbsent(new RemoveLower());
        cm.addIfAbsent(new Save());
        cm.addIfAbsent(new Show());
        cm.addIfAbsent(new Update());
        return cm;
    }

    /**
     * Возвращает менеджер комманд поумолчанию
     *
     * @param ticketCollection коллекция с которой будут работать команды
     * @param console          объект типа {@link Console} с помощью которого происходит взаимодействие с пользователем
     */
    public static CommandManager getClientCommandManager(TicketCollection ticketCollection, Console console) {
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
        Command command = getCommand(commandName);
        if (command == null) {
            System.out.println("Такой команды не существует. Для вызова справки введите: help");
            return;
        }
        console. (command);
        command.execute(args);
        command.clearArguments();
    }

    public Command getCommand(String commandName) {
        return commands.get(commandName.toLowerCase());
    }

    /**
     * Ожидает команду от пользователя и запускает её исполнение
     */
    public void run() {
        String t = console.nextLine().trim();
        if (t.isEmpty())
            return;
        String[] s = t.trim().split("\\s");
        executeCommand(s[0], Arrays.copyOfRange(s, 1, s.length));
    }

    /**
     * Заканчивает процесс
     */
    public void exit() {
        executeCommand("Save");
        // TODO: реальный выход из программы
        System.err.println("типо вышли из программы");
    }
}
