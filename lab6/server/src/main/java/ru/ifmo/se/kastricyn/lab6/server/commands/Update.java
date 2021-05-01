package ru.ifmo.se.kastricyn.lab6.server.commands;

import ru.ifmo.se.kastricyn.lab6.lib.data.Ticket;
import ru.ifmo.se.kastricyn.lab6.lib.Console;
import ru.ifmo.se.kastricyn.lab6.server.TicketCollection;

/**
 * Команда обновить значение элемента коллекции, id которого равен заданному
 */
public class Update extends AbstractCommand {
    private TicketCollection ticketCollection;
    private Console console;

    public Update(TicketCollection ticketCollection, Console console) {
        super("update", "обновить значение элемента коллекции, id которого равен заданному");
        this.ticketCollection = ticketCollection;
        this.console = console;
    }

    @Override
    public void execute(String... args) {
        //todo more information exceptions
        long id = -1;
        try {
            id = Long.parseLong(args[0]);
        } catch (NumberFormatException | IndexOutOfBoundsException Ie) {
            System.out.println("Команда принимает на вход только одно число типа long - id элемента коллекции");
            return;
        }
        if (!ticketCollection.hasElement(id)) {
            System.out.println("В коллекции нет элемента с таким id");
            return;
        }
        Ticket t = new Ticket(console);
        ticketCollection.update(id, t);
        System.out.println("Объект обновлён");
        ticketCollection.setSaved(false);
    }
}
