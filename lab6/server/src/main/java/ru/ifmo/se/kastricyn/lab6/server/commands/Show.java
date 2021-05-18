package ru.ifmo.se.kastricyn.lab6.server.commands;

import ru.ifmo.se.kastricyn.lab6.lib.utility.Console;
import ru.ifmo.se.kastricyn.lab6.server.ServerAbstractCommand;
import ru.ifmo.se.kastricyn.lab6.server.TicketCollection;

import java.util.stream.StreamSupport;

/**
 * Команда вывести в стандартный поток вывода все элементы коллекции в строковом представлении
 */
public class Show extends ServerAbstractCommand {

    public Show() {
        super("show", "вывести в стандартный поток вывода все элементы коллекции в строковом представлении");
        setNeedArgumentType(TicketCollection.class);
    }


    @Override
    public void execute(String... args) {
        TicketCollection ticketCollection = objArgs.getTicketCollection();

        if (ticketCollection.isEmpty())
            answer = "Коллекция пуста";
        else {
            ticketCollection.sort();
            answer = Console.getStringFromStream("Коллекция:",
                    StreamSupport.stream(ticketCollection.sort().spliterator(), true));
        }
    }
}
