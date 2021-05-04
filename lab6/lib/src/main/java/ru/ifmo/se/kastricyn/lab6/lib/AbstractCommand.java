package ru.ifmo.se.kastricyn.lab6.lib;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * Абстрактный класс для команд пользователя
 */
public abstract class AbstractCommand implements Command {
    protected final String name;
    protected final String description;
    protected String answer = "";

    protected final ArrayList<Class> argTypes = new ArrayList<>();
    protected ArrayList<Object> args = new ArrayList<>();

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
     * возвращает типы необходимых нестроковых аргументов команды пользователя
     */
    @Override
    public ArrayList<Class> getArgumentTypes() {
        return new ArrayList<>(argTypes);
    }

    /**
     * Устанавливает типы нестроковых аргументов команды
     *
     * @param argTypes типы аргументов команы
     * @return команду с аргументами
     */
    protected Command setArgumentTypes(Class... argTypes) {
        this.argTypes.clear();
        this.argTypes.addAll(Arrays.stream(argTypes).collect(Collectors.toList()));
        return this;
    }

    @Override
    public Command setArguments(Object... args) {
        this.args.clear();
        this.args.addAll(Arrays.stream(args).collect(Collectors.toList()));
        return this;
    }

    /**
     * возвращает аргументы команды
     */
    public ArrayList<Object> getArguments() {
        return new ArrayList<>(args);
    }

    @Override
    public void clearArguments() {
        args.clear();
    }

    /**
     * возвращает true, если у команды указаны верные параметры, инчае false
     */
    public boolean paramsIsValidate() {
        if (argTypes.size() == 0)
            if (args.size() != argTypes.size())
                return false;
        for (int i = 0; i < args.size(); i++) {
            if (!argTypes.get(i).isInstance(args.get(i)))
                return false;
        }
        return true;
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
    public String toString() {
        return name + "\n - " + description;
    }

    /**
     * @param args аргументы команды
     * @throws IndexOutOfBoundsException if paramsIsValidate()!=true
     */
    @Override
    public abstract void execute(String... args);

}
