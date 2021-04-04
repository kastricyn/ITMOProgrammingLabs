package ru.ifmo.se.kastricyn.commands;

import ru.ifmo.se.kastricyn.TicketCollection;

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
