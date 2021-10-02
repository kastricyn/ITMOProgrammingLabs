package ru.ifmo.se.kastricyn.lab8.client.console;

import org.jetbrains.annotations.NotNull;
import ru.ifmo.se.kastricyn.lab8.lib.connection.CommandArgument;

public class ClientCommandArgument extends CommandArgument {
    private ClientCommandManager commandManager;

    @Override
    public ClientCommandManager getCommandManager() {
        return commandManager;
    }

    public @NotNull ClientCommandArgument setCommandManager(ClientCommandManager commandManager) {
        this.commandManager = commandManager;
        return this;
    }
}
