package ru.ifmo.se.kastricyn.lab6.server.commands;

import ru.ifmo.se.kastricyn.lab6.lib.AbstractCommand;
import ru.ifmo.se.kastricyn.lab6.server.TicketCollection;

/**
 * Команда удалить элемент из коллекции по его id
 */
public class RemoveById extends AbstractCommand {

    public RemoveById() {
        super("remove_by_id", "удалить элемент из коллекции по его id");
        setArgumentTypes(TicketCollection.class);
    }


    @Override
    public void execute(String... args) {
        TicketCollection ticketCollection = (TicketCollection) this.args.get(0);
        //todo написать правильный help для команд с аргументами
        try {
            ticketCollection.remove(Long.parseLong(args[0]));
            ticketCollection.setSaved(false);
            answer = "Элемент удалён";
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            answer = "Команда принимает на вход аргумент - целочисленный id элемента коллекции";
        } catch (IllegalArgumentException e) {
            answer = "В коллекции нет элемента с таким id.";
        }
    }
}
