package ru.ifmo.se.kastricyn.lab6.server.commands;

import ru.ifmo.se.kastricyn.lab6.lib.data.Ticket;
import ru.ifmo.se.kastricyn.lab6.server.TicketCollection;

import java.util.Scanner;

public class AddClient extends Add {

    /**
     * Создаёт команду, которая для исполнения будет использовать переданные её аргументы
     *
     * @param ticketCollection
     * @param in
     * @param shouldPrintHints
     */
    public AddClient(TicketCollection ticketCollection, Scanner in, boolean shouldPrintHints) {
        super(ticketCollection, in, shouldPrintHints);
    }

//todo
}
