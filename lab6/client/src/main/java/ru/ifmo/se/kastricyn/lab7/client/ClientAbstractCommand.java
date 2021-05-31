package ru.ifmo.se.kastricyn.lab7.client;

import ru.ifmo.se.kastricyn.lab7.lib.AbstractCommand;

public abstract class ClientAbstractCommand extends AbstractCommand {
    protected ClientCommandArgument objArgs;

    /**
     * конструктор класса наседника, принимает на вход параметры, необходимые для реализации конкретной команды
     * вызвается из конструктора класса наследника
     *
     * @param name        имя команды
     * @param description описание команды
     */
    public ClientAbstractCommand(String name, String description) {
        super(name, description);
    }


    public ClientAbstractCommand setArguments(ClientCommandArgument args) {
        if (args == null)
            throw new NullPointerException();
        objArgs = args;
        return this;
    }

    public void clearArguments() {
        objArgs = new ClientCommandArgument();
    }
}
