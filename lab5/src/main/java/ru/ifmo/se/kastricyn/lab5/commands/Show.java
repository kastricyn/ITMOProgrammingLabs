package ru.ifmo.se.kastricyn.lab5.commands;

import ru.ifmo.se.kastricyn.lab5.TicketCollection;

/**
 * Команда вывести в стандартный поток вывода все элементы коллекции в строковом представлении
 */
public class Show extends AbstractCommand {
    private TicketCollection ticketCollection;

    public Show(TicketCollection ticketCollection) {
        super("show", "show \n - вывести в стандартный поток вывода все элементы коллекции в строковом представлении");
        this.ticketCollection = ticketCollection;
    }

    @Override
    public void execute(String... args) {
        if(ticketCollection.isEmpty())
            System.out.println("Коллекция пуста");
        ticketCollection.forEach(System.out::println);
    }
}
