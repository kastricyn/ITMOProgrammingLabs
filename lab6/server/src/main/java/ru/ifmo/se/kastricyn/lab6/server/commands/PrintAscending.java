package ru.ifmo.se.kastricyn.lab6.server.commands;

import ru.ifmo.se.kastricyn.lab6.lib.AbstractCommand;
import ru.ifmo.se.kastricyn.lab6.lib.utility.Console;
import ru.ifmo.se.kastricyn.lab6.server.TicketCollection;

import java.util.stream.StreamSupport;

/**
 * Команда вывести элементы коллекции в порядке возрастания
 */
public class PrintAscending extends AbstractCommand {

    public PrintAscending() {
        super("print_ascending", "вывести элементы коллекции в порядке возрастания");

        setArgumentTypes(TicketCollection.class);
    }


    @Override
    public void execute(String... args) {
        TicketCollection ticketCollection = (TicketCollection) this.args.get(0);
        if (ticketCollection.isEmpty()) {
            answer = "Коллекция пуста";
            return;
        }
        answer = Console.getStringFromStream("Элементы коллекции в порядке возрастания:\n",
                StreamSupport.stream(ticketCollection.spliterator(), true).sorted());
    }
}
