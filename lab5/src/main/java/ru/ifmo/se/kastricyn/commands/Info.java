package ru.ifmo.se.kastricyn.commands;

import ru.ifmo.se.kastricyn.Command;
import ru.ifmo.se.kastricyn.TicketCollection;

public class Info extends AbstractCommand {
    private TicketCollection ticketCollection;
    public Info(String name, String description) {
        super("info", "info \n - вывести в стандартный поток вывода информацию о коллекции (тип, дата инициализации, количество элементов и т.д.)");
    }

    @Override
    public void execute(String ... args) {
        System.out.println("здесь будет info");


    }
}
