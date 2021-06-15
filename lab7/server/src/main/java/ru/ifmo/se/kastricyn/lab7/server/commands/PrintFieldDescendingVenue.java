package ru.ifmo.se.kastricyn.lab7.server.commands;

import ru.ifmo.se.kastricyn.lab7.lib.data.Ticket;
import ru.ifmo.se.kastricyn.lab7.lib.utility.Console;
import ru.ifmo.se.kastricyn.lab7.server.TicketCollection;

import java.util.Comparator;
import java.util.stream.StreamSupport;

/**
 * Команда вывести значения поля venue всех элементов в порядке убывания
 */
public class PrintFieldDescendingVenue extends CommandWithAuth {

    public PrintFieldDescendingVenue() {
        super("print_field_descending_venue", "вывести значения поля venue всех элементов в порядке убывания");
        setNeedArgumentType(TicketCollection.class);
    }


    @Override
    public void execute(String... args) {
        if(!auth())
            return;
        TicketCollection ticketCollection = objArgs.getTicketCollection();

        answer = Console.getStringFromStream("Поля venue всех элементов в порядке убывания:\n",
                StreamSupport.stream(ticketCollection.spliterator(), true)
                        .map(Ticket::getVenue)
                        .distinct()
                        .sorted(Comparator.reverseOrder()));
    }
}
