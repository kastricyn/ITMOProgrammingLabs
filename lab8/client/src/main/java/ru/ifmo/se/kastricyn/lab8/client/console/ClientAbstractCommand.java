package ru.ifmo.se.kastricyn.lab8.client.console;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.ifmo.se.kastricyn.lab8.lib.AbstractCommand;

public abstract class ClientAbstractCommand extends AbstractCommand {
    protected @Nullable ClientCommandArgument objArgs;

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


    public @NotNull ClientAbstractCommand setArguments(@NotNull ClientCommandArgument args) {
        objArgs = args;
        return this;
    }

    public void clearArguments() {
        objArgs = new ClientCommandArgument();
    }
}
