package ru.ifmo.se.kastricyn.lab5.commands;

import ru.ifmo.se.kastricyn.lab5.TicketCollection;
import ru.ifmo.se.kastricyn.lab5.data.Ticket;

import java.util.Iterator;

/**
 * Команда вывести элементы коллекции в порядке возрастания
 */
public class PrintAscending extends AbstractCommand {
    private TicketCollection ticketCollection;

    public PrintAscending(TicketCollection ticketCollection) {
        super("print_ascending", "print_ascending \n - вывести элементы коллекции в порядке возрастания");
        this.ticketCollection = ticketCollection;
    }

    @Override
    public void execute(String... args) {
        if(ticketCollection.isEmpty()){
            System.out.println("Коллекция пуста");
            return;
        }
        ticketCollection.sort();
        Iterator<Ticket> iterator = ticketCollection.iterator();
        while (iterator.hasNext())
            System.out.println(iterator.next());
    }
}
