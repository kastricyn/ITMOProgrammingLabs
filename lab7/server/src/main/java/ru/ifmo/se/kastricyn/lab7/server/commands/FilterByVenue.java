package ru.ifmo.se.kastricyn.lab7.server.commands;

import ru.ifmo.se.kastricyn.lab7.lib.data.Ticket;
import ru.ifmo.se.kastricyn.lab7.lib.data.Venue;
import ru.ifmo.se.kastricyn.lab7.lib.utility.Console;
import ru.ifmo.se.kastricyn.lab7.server.TicketCollection;

import java.util.function.Supplier;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

/**
 * Команда вывести элементы, значение поля venue которых равно заданному
 */
public class FilterByVenue extends CommandWithAuth {


    public FilterByVenue() {
        super("filter_by_venue", "вывести элементы, значение поля venue которых равно заданному");

        setNeedArgumentType(TicketCollection.class, Venue.class);
    }

    @Override
    public void execute(String... args) {
        if(!auth())
            return;
        assert objArgs != null;
        TicketCollection ticketCollection = objArgs.getTicketCollection();
        Venue venue = objArgs.getVenue();


        Supplier<Stream<Ticket>> v = () -> StreamSupport.stream(ticketCollection.spliterator(), true).filter(x -> x.getVenue().equals(venue));
        if (v.get().findAny().isPresent()) {
            answer = Console.getStringFromStream("Элементы имеющие введённый venue:", v.get());

        } else
            answer = "В коллекции нет элементов, имеющие введённый venue.";
    }
}
