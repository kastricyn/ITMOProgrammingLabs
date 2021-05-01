package ru.ifmo.se.kastricyn.lab6.server.commands;

import ru.ifmo.se.kastricyn.lab6.lib.Console;
import ru.ifmo.se.kastricyn.lab6.server.TicketCollection;

/**
 * Команда выйти из программы
 */
public class Exit extends AbstractCommand {
    TicketCollection ticketCollection;
    Console console;

    public Exit(TicketCollection ticketCollection, Console console) {
        super("exit", "завершить программу (без сохранения в файл)");
        this.ticketCollection = ticketCollection;
        this.console = console;
    }

    @Override
    public void execute(String... args) {
        if (console.isInteractiveMode() && !ticketCollection.isSaved()) {
            if (console.requestConfirmation("Вы действительно хотите выйти без сохранения")) System.exit(0);
        } else
            System.exit(0);
    }

}
