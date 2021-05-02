package ru.ifmo.se.kastricyn.lab6.server.commands;

import ru.ifmo.se.kastricyn.lab6.lib.AbstractCommand;
import ru.ifmo.se.kastricyn.lab6.lib.utility.Parser;
import ru.ifmo.se.kastricyn.lab6.server.TicketCollection;


/**
 * Команда сохранить коллекцию в файл, путь до которого передан при запуске программы
 */
public class Save extends AbstractCommand {
    TicketCollection ticketCollection;

    public Save(TicketCollection ticketCollection) {
        super("save", "сохранить коллекцию в файл");
        this.ticketCollection = ticketCollection;
    }

    @Override
    public void execute(String... args) {
        boolean flag = ticketCollection.isSaved();
        try {
            ticketCollection.setSaved(true);
            Parser.write(ticketCollection.getPath(), TicketCollection.class, ticketCollection);
            System.out.println("Сохранено.");
        } catch (Exception e) {
            System.out.println("Сохранить не удалось.");
            ticketCollection.setSaved(flag);
        }
    }
}
