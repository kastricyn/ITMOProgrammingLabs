package ru.ifmo.se.kastricyn.lab6.server.commands;

import ru.ifmo.se.kastricyn.lab6.lib.AbstractCommand;
import ru.ifmo.se.kastricyn.lab6.lib.data.Ticket;
import ru.ifmo.se.kastricyn.lab6.lib.utility.Console;
import ru.ifmo.se.kastricyn.lab6.server.TicketCollection;

import java.util.*;
import java.util.stream.StreamSupport;

/**
 * Команда добавить элемент в колекцию, если он больше всех элементов в колекции
 */
public class AddIfMax extends AbstractCommand {
    private Scanner in;
    private boolean shouldPrintHints;
    private TicketCollection ticketCollection;

    public AddIfMax(TicketCollection ticketCollection, Scanner in, boolean shouldPrintHints) {
        super("add_if_max", "добавить новый элемент в коллекцию, если его значение превышает значение наибольшего элемента этой коллекции");
        this.in = in;
        this.shouldPrintHints = shouldPrintHints;
        this.ticketCollection = ticketCollection;
    }

    @Override
    public void execute(String... args) {
        Ticket maxTicket = new Ticket(new Console(in, shouldPrintHints));
        Optional<Ticket> t = StreamSupport.stream(ticketCollection.spliterator(), true).max(Comparator.naturalOrder());
        if (t.isPresent())
            if (t.get().compareTo(maxTicket) < 0) {
                ticketCollection.add(maxTicket);
                System.out.println("В коллекцю добавлен объект " + t);
            } else
                System.out.println("Коллекция не изменена (данный элемент не больше наибольшего).");
        else
            System.out.println("Коллекция пуста (=> наибольшего эллемента нет), используйте команду add.");

        ticketCollection.setSaved(false);
    }
}
