package ru.ifmo.se.kastricyn.lab8.server.commands;


import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.ifmo.se.kastricyn.lab8.lib.AbstractCommand;
import ru.ifmo.se.kastricyn.lab8.lib.CommandManager;
import ru.ifmo.se.kastricyn.lab8.lib.User;
import ru.ifmo.se.kastricyn.lab8.lib.data.Ticket;
import ru.ifmo.se.kastricyn.lab8.lib.data.Venue;
import ru.ifmo.se.kastricyn.lab8.server.ServerCommandArgument;
import ru.ifmo.se.kastricyn.lab8.server.TicketCollection;

public abstract class ServerAbstractCommand extends AbstractCommand {
    protected volatile @Nullable ServerCommandArgument objArgs;

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

    public @NotNull ServerAbstractCommand setArguments(@Nullable ServerCommandArgument args) {
        if (args == null)
            throw new NullPointerException();
        objArgs = args;
        return this;
    }

    public void clearArguments() {
        objArgs = new ServerCommandArgument();
    }

    /**
     * возвращает true, если у команды указаны верные параметры, инчае false
     */
    @Override
    public boolean objectsArgsIsValidate() {
        return (!argTypes.contains(Ticket.class) || objArgs.getTicket() != null) &&
                (!argTypes.contains(Venue.class) || objArgs.getVenue() != null) &&
                (!argTypes.contains(CommandManager.class) || objArgs.getCommandManager() != null) &&
                (!argTypes.contains(TicketCollection.class) || objArgs.getTicketCollection() != null) &&
                (!argTypes.contains(User.class) || objArgs.getUser() != null);
    }
}
