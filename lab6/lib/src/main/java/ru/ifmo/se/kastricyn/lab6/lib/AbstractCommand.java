package ru.ifmo.se.kastricyn.lab6.lib;

import java.util.ArrayList;

/**
 * Абстрактный класс для команд пользователя
 */
public abstract class AbstractCommand implements Command {
    final String name;
    protected String answer = "";
    final String description;

    protected final ArrayList<Class> paramTypes = new ArrayList<>();
    protected ArrayList<Object> params = new ArrayList<>();

    /**
     * вызвается из конструктора класса наследника, конструктор класса наседника принимает на вход параметры, необходимые для реализации конкретной команды
     *
     * @param name        - имя команды
     * @param description - описание команды
     */
    public AbstractCommand(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public ArrayList<Class> getParamTypes() {
        return new ArrayList<>(paramTypes);
    }

    protected Command setParamTypes(ArrayList<Class> paramTypes){
        paramTypes.clear();
        paramTypes.addAll(paramTypes);
        return this;
    }

    public Command setParams(ArrayList<? extends Object> params) {
        this.params.clear();
        this.params.addAll(params);
        return this;
    }

    public ArrayList<Object> getParams() {
        return new ArrayList<>(params);
    }

    /**
     * @return true, если у команды указаны верные параметры, инчае false
     */
    public boolean paramsIsValidate() {
        if(paramTypes.size()==0)
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
    /**
     *
     * @param args аргументы команды
     * @throws IndexOutOfBoundsException if paramsIsValidate()!=true
     */
    @Override
    public abstract void execute(String ... args);

}
