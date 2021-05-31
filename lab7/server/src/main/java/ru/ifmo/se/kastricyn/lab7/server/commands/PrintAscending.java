package ru.ifmo.se.kastricyn.lab7.server.commands;

import ru.ifmo.se.kastricyn.lab7.lib.utility.Console;
import ru.ifmo.se.kastricyn.lab7.server.TicketCollection;

import java.util.stream.StreamSupport;

/**
 * Команда вывести элементы коллекции в порядке возрастания
 */
public class PrintAscending extends ServerAbstractCommand {

    public PrintAscending() {
        super("print_ascending", "вывести элементы коллекции в порядке возрастания");

        setNeedArgumentType(TicketCollection.class);
    }


    @Override
    public void execute(String... args) {
        TicketCollection ticketCollection = objArgs.getTicketCollection();
        if (ticketCollection.isEmpty()) {
            answer = "Коллекция пуста";
            return;
        }
        answer = Console.getStringFromStream("Элементы коллекции в порядке возрастания:\n",
                StreamSupport.stream(ticketCollection.spliterator(), true).sorted());
    }
}
