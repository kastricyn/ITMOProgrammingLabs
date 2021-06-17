package ru.ifmo.se.kastricyn.lab7.server.commands;

import org.jetbrains.annotations.NotNull;
import ru.ifmo.se.kastricyn.lab7.server.TicketCollection;

/**
 * Команда удалить элемент из коллекции по его id
 */
public class RemoveById extends CommandWithAuth {
    //todo написать правильный help для команд с аргументами

    public RemoveById() {
        super("remove_by_id", "удалить элемент из коллекции по его id");
        setNeedArgumentType(TicketCollection.class);
    }


    @Override
    public synchronized void execute(String... args) {
        if (!auth())
            return;
        assert objArgs != null;
        TicketCollection ticketCollection = objArgs.getTicketCollection();
        try {
            long ticketId = Long.parseLong(args[0]);
            if (ticketCollection.getElement(ticketId).getUserId() != objArgs.getUser().getId()) {
                answer = "Манипуляция возможна только над своими объектами.";
                return;
            }
            if (!ticketCollection.getDb().deleteTicket(ticketId)) {
                answer = "Удаление не удалось";
                return;
            }
            ticketCollection.remove(ticketId);
            answer = "Элемент удалён";
        } catch (@NotNull NumberFormatException | IndexOutOfBoundsException e) {
            answer = "Команда принимает на вход аргумент - целочисленный id элемента коллекции";
        } catch (IllegalArgumentException e) {
            answer = "В коллекции нет элемента с таким id.";
        }
    }
}
