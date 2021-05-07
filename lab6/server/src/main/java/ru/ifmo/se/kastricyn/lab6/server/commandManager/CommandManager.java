package ru.ifmo.se.kastricyn.lab6.server.commandManager;


import ru.ifmo.se.kastricyn.lab6.lib.AbstractCommand;
import ru.ifmo.se.kastricyn.lab6.lib.Command;
import ru.ifmo.se.kastricyn.lab6.server.TicketCollection;

import java.util.HashMap;
import java.util.stream.Stream;

/**
 * Исполнитель команд. Класс реализует управление коммандами, доступными для пользователя.
 * Разные объекты этого класса могут по-разному исполнять комманды.
 */
public abstract class CommandManager implements Runnable {
    protected HashMap<String, AbstractCommand> commands;

    protected TicketCollection ticketCollection;

    /**
     * Создаёт менеджер коммандами.
     *
     * @param ticketCollection коллекция с которой будут работать команды
     */
    public CommandManager(TicketCollection ticketCollection) {
        commands = new HashMap<>();
        this.ticketCollection = ticketCollection;
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

    public Command getCommand(String commandName) {
        return commands.get(commandName.toLowerCase());
    }


    /**
     * Исполняет команду имя которой передано в первом аргументе
     *
     * @param commandName имя команды
     * @param args        аргументы команды в строковом представлении
     */
    public abstract void executeCommand(String commandName, String... args);

    /**
     * Обрабатывает команды, пока они поступают от пользователя.
     * Надо переопределить для конкртной реализации
     */
    public abstract void run();

    /**
     * Иполняется при попытке выхода пользователя из программы (команда exit, ctrl+D)
     */
    public void exit() {
//        executeCommand("Save");
        // TODO: реальный выход из программы
        System.err.println("типо вышли из программы");
    }
}
