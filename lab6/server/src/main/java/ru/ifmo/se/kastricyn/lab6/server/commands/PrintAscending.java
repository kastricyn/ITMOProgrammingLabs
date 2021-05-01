package ru.ifmo.se.kastricyn.lab6.server.commands;

import ru.ifmo.se.kastricyn.lab6.server.TicketCollection;

import java.util.stream.StreamSupport;

/**
 * Команда вывести элементы коллекции в порядке возрастания
 */
public class PrintAscending extends AbstractCommand {
    private TicketCollection ticketCollection;

    public PrintAscending(TicketCollection ticketCollection) {
        super("print_ascending", "вывести элементы коллекции в порядке возрастания");
        this.ticketCollection = ticketCollection;
    }

    @Override
    public void execute(String... args) {
        if(ticketCollection.isEmpty()){
            System.out.println("Коллекция пуста");
            return;
        }
        StreamSupport.stream(ticketCollection.spliterator(), true).sorted().forEachOrdered(System.out::println);
    }
}
