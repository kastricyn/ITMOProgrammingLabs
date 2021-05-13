package ru.ifmo.se.kastricyn.lab6.server;


import ru.ifmo.se.kastricyn.lab6.lib.AbstractCommand;

public abstract class ServerAbstractCommand extends AbstractCommand {
    protected ServerCommandArgument objArgs;

    /**
     * конструктор класса наседника, принимает на вход параметры, необходимые для реализации конкретной команды
     * вызвается из конструктора класса наследника
     *
     * @param name        имя команды
     * @param description описание команды
     */
    public ServerAbstractCommand(String name, String description) {
        super(name, description);
    }

    public ServerAbstractCommand setArguments(ServerCommandArgument args) {
        if (args == null)
            throw new NullPointerException();
        objArgs = args;
        return this;
    }

    public void clearArguments() {
        objArgs = new ServerCommandArgument();
    }

}
