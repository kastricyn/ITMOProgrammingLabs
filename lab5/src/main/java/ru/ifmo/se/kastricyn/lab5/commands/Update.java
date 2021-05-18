package ru.ifmo.se.kastricyn.lab5.commands;

import ru.ifmo.se.kastricyn.lab5.TicketCollection;
import ru.ifmo.se.kastricyn.lab5.data.Ticket;
import ru.ifmo.se.kastricyn.lab5.utility.Console;

/**
 * Команда обновить значение элемента коллекции, id которого равен заданному
 */
public class Update extends AbstractCommand {
    private TicketCollection ticketCollection;
    private Console console;

    public Update(TicketCollection ticketCollection, Console console) {
        super("update", "update id {element} \n - обновить значение элемента коллекции, id которого равен заданному");
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
