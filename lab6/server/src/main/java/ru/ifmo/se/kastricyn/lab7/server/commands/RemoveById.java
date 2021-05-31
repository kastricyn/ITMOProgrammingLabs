package ru.ifmo.se.kastricyn.lab7.server.commands;

import ru.ifmo.se.kastricyn.lab7.server.ServerAbstractCommand;
import ru.ifmo.se.kastricyn.lab7.server.TicketCollection;

/**
 * Команда удалить элемент из коллекции по его id
 */
public class RemoveById extends ServerAbstractCommand {

    public RemoveById() {
        super("remove_by_id", "удалить элемент из коллекции по его id");
        setNeedArgumentType(TicketCollection.class);
    }


    @Override
    public void execute(String... args) {
        TicketCollection ticketCollection = objArgs.getTicketCollection();

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
