package ru.ifmo.se.kastricyn.lab6.server.commands;

import ru.ifmo.se.kastricyn.lab6.lib.AbstractCommand;
import ru.ifmo.se.kastricyn.lab6.lib.utility.Director;
import ru.ifmo.se.kastricyn.lab6.server.TicketCollection;

/**
 * Команда выйти из программы
 */
public class Exit extends AbstractCommand {
    TicketCollection ticketCollection;
    Director director;

    public Exit(TicketCollection ticketCollection, Director director) {
        super("exit", "завершить программу (без сохранения в файл)");
        this.ticketCollection = ticketCollection;
        this.director = director;
    }

    @Override
    public void execute(String... args) {
        if (director.isInteractiveMode() && !ticketCollection.isSaved()) {
            if (director.requestConfirmation("Вы действительно хотите выйти без сохранения")) System.exit(0);
        } else
            System.exit(0);
    }

}
