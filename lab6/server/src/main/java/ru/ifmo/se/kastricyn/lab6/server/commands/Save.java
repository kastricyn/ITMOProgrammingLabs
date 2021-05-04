package ru.ifmo.se.kastricyn.lab6.server.commands;

import ru.ifmo.se.kastricyn.lab6.lib.AbstractCommand;
import ru.ifmo.se.kastricyn.lab6.lib.utility.Parser;
import ru.ifmo.se.kastricyn.lab6.server.TicketCollection;

/**
 * Команда сохранить коллекцию в файл, путь до которого передан при запуске программы
 */
public class Save extends AbstractCommand {

    public Save() {
        super("save", "сохранить коллекцию в файл");
        setArgumentTypes(TicketCollection.class);
    }


    @Override
    public void execute(String... args) {
        TicketCollection ticketCollection = (TicketCollection) this.args.get(0);
        boolean isSaved = ticketCollection.isSaved();
        try {
            ticketCollection.setSaved(true);
            Parser.write(ticketCollection.getPath(), TicketCollection.class, ticketCollection);
            answer = "Сохранено.";
        } catch (Exception e) {
            answer = "Сохранить не удалось.";
            ticketCollection.setSaved(isSaved);
        }
    }
}
