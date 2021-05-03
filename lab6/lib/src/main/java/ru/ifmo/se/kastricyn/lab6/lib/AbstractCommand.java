package ru.ifmo.se.kastricyn.lab6.lib;

/**
 * Абстрактный класс для команд пользователя
 */
public abstract class AbstractCommand<T> implements Command {
    final String name;
    final String description;
    protected T param;

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

    /**
     * Надо переопределить команду у наследника, если необходим параметр
     * @return
     */
    protected T getParam() {
        return null;
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
