package ru.ifmo.se.kastricyn.lab7.client;

import org.jetbrains.annotations.NotNull;
import ru.ifmo.se.kastricyn.lab7.lib.CommandArgument;

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
