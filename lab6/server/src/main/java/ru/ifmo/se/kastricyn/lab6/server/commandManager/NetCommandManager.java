package ru.ifmo.se.kastricyn.lab6.server.commandManager;

import ru.ifmo.se.kastricyn.lab6.lib.utility.Console;
import ru.ifmo.se.kastricyn.lab6.server.TicketCollection;

public class NetCommandManager extends CommandManager {

    public NetCommandManager(TicketCollection ticketCollection) {
        super(ticketCollection);
    }

    /**
     * Возвращает менеджер комманд поумолчанию
     *
     * @param ticketCollection коллекция с которой будут работать команды
     * @param console          объект типа {@link Console} с помощью которого происходит взаимодействие с пользователем
     */
    public static CommandManager getClientCommandManager(TicketCollection ticketCollection, Console console) {
        //todo
        return null;
    }
}
