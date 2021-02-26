package ru.ifmo.se.kastricyn.cmd;

import ru.ifmo.se.kastricyn.Command;
import ru.ifmo.se.kastricyn.TicketCollection;

public class Info implements Command {

    private final TicketCollection ticketCollection;

    public Info(TicketCollection ticketCollection) {
        this.ticketCollection = ticketCollection;
    }

    @Override
    public void execute() {
        System.out.println("здесь будет info");
        // todo

    }
}
