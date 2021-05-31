package ru.ifmo.se.kastricyn.lab7.lib;

import org.jetbrains.annotations.NotNull;
import ru.ifmo.se.kastricyn.lab7.lib.connection.CommandArgument;
import ru.ifmo.se.kastricyn.lab7.lib.data.Ticket;
import ru.ifmo.se.kastricyn.lab7.lib.data.Venue;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Абстрактный класс для команд пользователя
 */
public abstract class AbstractCommand implements Command {
    protected final String name;
    protected final String description;
    protected final Set<Class> argTypes = new HashSet<>();
    protected String answer = "";

    protected CommandArgument objArgs = new CommandArgument();

    /**
     * конструктор класса наседника, принимает на вход параметры, необходимые для реализации конкретной команды
     * вызвается из конструктора класса наследника
     *
     * @param name        имя команды
     * @param description описание команды
     */
    public AbstractCommand(String name, String description) {
        this.name = name;
        this.description = description;
    }


    /**
     * возвращает типы нестроковых необходимых аргументов команды
     */
    @Override
    public @NotNull Set<Class> getArgumentTypes() {
        return argTypes;
    }

    /**
     * Устанавливает типы нестроковых аргументов команды
     *
     * @param argTypes типы аргументов команд
     * @return команду с аргументами
     */
    protected @NotNull AbstractCommand setNeedArgumentType(Class @NotNull ... argTypes) {
        this.argTypes.clear();
        this.argTypes.addAll(Arrays.stream(argTypes).collect(Collectors.toSet()));
        return this;
    }

    @Override
    public @NotNull AbstractCommand setArguments(CommandArgument args) {
        objArgs = args;
        return this;
    }

    @Override
    public void clearArguments() {
        objArgs = new CommandArgument();
    }

    /**
     * возвращает true, если у команды указаны верные параметры, инчае false
     */
    public boolean objectsArgsIsValidate() {
        return (!argTypes.contains(Ticket.class) || objArgs.getTicket() != null) &&
                (!argTypes.contains(Venue.class) || objArgs.getVenue() != null) &&
                (!argTypes.contains(CommandManager.class) || objArgs.getCommandManager() != null);
    }

    /**
     * возвращает строку с ответом команды ползователю
     */
    @Override
    public String getAnswer() {
        return answer;
    }

    /**
     * Возвращает имя команды
     */
    public String getName() {
        return name;
    }

    /**
     * Возвращает  описание команды
     */
    public String getDescription() {
        return description;
    }
    // todo: description is only description; toString = name + description

    /**
     * Возвращает строку для представления команды в справке:
     * <code>name -
     * description</code>
     */
    @Override
    public @NotNull String toString() {
        return name + "\n - " + description;
    }

    /**
     * @param args аргументы команды
     */
    @Override
    public abstract void execute(String... args);

}
