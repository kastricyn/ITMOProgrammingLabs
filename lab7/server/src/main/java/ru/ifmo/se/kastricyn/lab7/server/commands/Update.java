package ru.ifmo.se.kastricyn.lab7.server.commands;

import org.jetbrains.annotations.NotNull;
import ru.ifmo.se.kastricyn.lab7.lib.data.Ticket;
import ru.ifmo.se.kastricyn.lab7.server.TicketCollection;

/**
 * Команда обновить значение элемента коллекции, id которого равен заданному
 */
public class Update extends CommandWithAuth {

    public Update() {
        super("update", "обновить значение элемента коллекции, id которого равен заданному");

        setNeedArgumentType(TicketCollection.class, Ticket.class);
    }


    @Override
    public void execute(String... args) {
        if(!auth())
            return;
        assert objArgs != null;
        TicketCollection ticketCollection = objArgs.getTicketCollection();
        Ticket t = objArgs.getTicket();

        long id = -1;
        try {
            id = Long.parseLong(args[0]);
            if (ticketCollection.getElement(id).getUserId() != objArgs.getUser().getId()) {
                answer = "Манипуляция возможна только над своими объектами.";
                return;
            }
            if(!ticketCollection.getDb().updateTicket(id, t)){
                answer = "Обновление не удалось";
                return;
            }
            ticketCollection.update(id, t);
        } catch (@NotNull NumberFormatException | IndexOutOfBoundsException Ie) {
            answer = "Команда принимает на вход только одно число типа long - id элемента коллекции";
            return;
        } catch (IllegalArgumentException e) {
            answer = "В коллекции нет элемента с таким id";
            return;
        }
        answer = "Объект обновлён";
        ticketCollection.setSaved(false);
    }
}
