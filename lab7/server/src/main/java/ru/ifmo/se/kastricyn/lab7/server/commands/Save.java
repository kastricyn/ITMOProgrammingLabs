package ru.ifmo.se.kastricyn.lab7.server.commands;

import ru.ifmo.se.kastricyn.lab7.server.TicketCollection;

/**
 * Команда сохранить коллекцию в файл, путь до которого передан при запуске программы
 */
//todo delete this command
public class Save extends ServerAbstractCommand {

    public Save() {
        super("save", "сохранить коллекцию в файл");
        setNeedArgumentType(TicketCollection.class);
    }


    @Override
    public void execute(String... args) {
        TicketCollection ticketCollection = objArgs.getTicketCollection();

        boolean isSaved = ticketCollection.isSaved();
        try {
            ticketCollection.setSaved(true);
//      todo us bd      Parser.write(ticketCollection.getPath(), TicketCollection.class, ticketCollection);
            answer = "Сохранено.";
        } catch (Exception e) {
            answer = "Сохранить не удалось.";
            ticketCollection.setSaved(isSaved);
            e.printStackTrace();
        }
    }
}
