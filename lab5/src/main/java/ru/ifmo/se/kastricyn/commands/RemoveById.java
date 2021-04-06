package ru.ifmo.se.kastricyn.commands;

import ru.ifmo.se.kastricyn.TicketCollection;

public class RemoveById extends AbstractCommand {
    private TicketCollection ticketCollection;

    public RemoveById(TicketCollection ticketCollection) {
        super("remove_by_id", "remove_by_id id \n - удалить элемент из коллекции по его id");
        this.ticketCollection = ticketCollection;
    }

    @Override
    public void execute(String... args) {
        //todo write more information exceptions:не правильный формат аргумента, нет эллемента,
        try {
            ticketCollection.remove(Long.parseLong(args[0]));
            ticketCollection.setSaved(false);
            System.out.println("Элемент удалён");
        } catch (Exception e) {
            System.out.println("Команда не выполнена, проверьте правильность аргументов.");
        }
    }
}
