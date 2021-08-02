package ru.ifmo.se.kastricyn.lab8.server.commands;

import ru.ifmo.se.kastricyn.lab8.lib.utility.Console;
import ru.ifmo.se.kastricyn.lab8.server.TicketCollection;

import java.util.stream.StreamSupport;

/**
 * Команда вывести элементы коллекции в порядке возрастания
 */
public class PrintAscending extends CommandWithAuth {

    public PrintAscending() {
        super("print_ascending", "вывести элементы коллекции в порядке возрастания");

        setNeedArgumentType(TicketCollection.class);
    }


    @Override
    public synchronized void execute(String... args) {
        if (!auth())
            return;
        TicketCollection ticketCollection = objArgs.getTicketCollection();
        if (ticketCollection.isEmpty()) {
            answer = "Коллекция пуста";
            return;
        }
        answer = Console.getStringFromStream("Элементы коллекции в порядке возрастания:\n",
                StreamSupport.stream(ticketCollection.spliterator(), true).sorted());
    }
}
