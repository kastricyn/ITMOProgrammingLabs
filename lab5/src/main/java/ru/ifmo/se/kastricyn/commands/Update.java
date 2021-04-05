package ru.ifmo.se.kastricyn.commands;

import ru.ifmo.se.kastricyn.TicketCollection;
import ru.ifmo.se.kastricyn.data.Ticket;
import ru.ifmo.se.kastricyn.utility.Console;

import java.util.Scanner;

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
            id = Long.parseLong(console.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Неправильная конфигурация параметров, для справки вызовите help");
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
