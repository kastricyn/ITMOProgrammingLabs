package ru.ifmo.se.kastricyn.lab6.server.commands;

import ru.ifmo.se.kastricyn.lab6.lib.data.Ticket;
import ru.ifmo.se.kastricyn.lab6.server.ServerAbstractCommand;
import ru.ifmo.se.kastricyn.lab6.server.TicketCollection;

/**
 * Команда обновить значение элемента коллекции, id которого равен заданному
 */
public class Update extends ServerAbstractCommand {

    public Update() {
        super("update", "обновить значение элемента коллекции, id которого равен заданному");

        setNeedArgumentType(TicketCollection.class, Ticket.class);
    }


    @Override
    public void execute(String... args) {
        TicketCollection ticketCollection = objArgs.getTicketCollection();
        Ticket t = objArgs.getTicket();

        long id = -1;
        try {
            id = Long.parseLong(args[0]);
            ticketCollection.update(id, t);
        } catch (NumberFormatException | IndexOutOfBoundsException Ie) {
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
