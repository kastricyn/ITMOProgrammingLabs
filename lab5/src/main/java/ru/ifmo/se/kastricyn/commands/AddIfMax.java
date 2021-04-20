package ru.ifmo.se.kastricyn.commands;

import ru.ifmo.se.kastricyn.TicketCollection;
import ru.ifmo.se.kastricyn.data.Ticket;
import ru.ifmo.se.kastricyn.utility.Console;

import java.util.Iterator;
import java.util.Scanner;

/**
 * Команда добавить элемент в колекцию, если он больше всех элементов в колекции
 */
public class AddIfMax extends AbstractCommand {
    private Scanner in;
    private boolean shouldPrintHints;
    private TicketCollection ticketCollection;

    public AddIfMax(TicketCollection ticketCollection, Scanner in, boolean shouldPrintHints) {
        super("add_if_max", "add_if_max {element} \n - добавить новый элемент в коллекцию, если его значение превышает значение наибольшего элемента этой коллекции");
        this.in = in;
        this.shouldPrintHints = shouldPrintHints;
        this.ticketCollection = ticketCollection;
    }


    @Override
    public void execute(String... args) {
        if (ticketCollection.isEmpty()) {
            System.out.println("Коллекция пуста (=> наибольшего эллемента нет), используйте команду add.");
            return;
        }

        //получим наибольший ticket
        Iterator<Ticket> iterator = ticketCollection.iterator();
        Ticket t;
        Ticket maxTicket = new Ticket(new Console(in, shouldPrintHints));
        do {
            t = iterator.next();
            if (t.compareTo(maxTicket) <= 0) {
                System.out.println("Коллекция не изменена (данный элемент не больше наибольшего).");
                return;
            }
        } while (iterator.hasNext());
        ticketCollection.add(t);
        System.out.println("В коллекцю добавлен объект " + t);
        ticketCollection.setSaved(false);
    }
}
