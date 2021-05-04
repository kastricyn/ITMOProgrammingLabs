package ru.ifmo.se.kastricyn.lab6.lib;

import java.util.ArrayList;
import java.util.function.BooleanSupplier;

/**
 * Абстрактный класс для команд пользователя
 */
public abstract class AbstractCommand<T> implements Command {
    final String name;
    final String description;

    protected static final ArrayList<Class> paramTypes = new ArrayList<>();
    protected ArrayList<Object> params;


    /**
     * вызвается из конструктора класса наследника, конструктор класса наседника принимает на вход параметры, необходимые для реализации конкретной команды
     *
     * @param name        - имя команды
     * @param description - описание команды
     */
    public AbstractCommand(String name, String description) {
        this.name = name;
        this.description = description;
        params = new ArrayList<>();

    }


    protected void setParam(ArrayList<?> params) {
        this.params.clear();
        this.params.add(params);
    }

    /**
     * @return true, если у команды указаны верные параметры, инчае false
     */
    protected boolean paramIsValidate() {
        if (params.size() != paramTypes.size())
            return false;
        for (int i = 0; i < params.size(); i++) {
            if (!paramTypes.get(i).isInstance(params.get(i)))
                return false;
        }
        return true;
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
}
