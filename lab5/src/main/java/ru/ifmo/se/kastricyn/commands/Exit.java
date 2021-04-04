package ru.ifmo.se.kastricyn.commands;

import ru.ifmo.se.kastricyn.TicketCollection;

import java.util.Scanner;

public class Exit extends AbstractCommand {
    TicketCollection ticketCollection;
    Scanner in;

    public Exit(TicketCollection ticketCollection, Scanner in) {
        super("exit", "exit \n - завершить программу (без сохранения в файл)");
        this.ticketCollection = ticketCollection;
        this.in = in;
    }

    @Override
    public void execute(String... args) {
        if (ticketCollection.isSaved()) {
            System.out.println("Коллекция была изменена, вы уверены, что хотите выйти без сохранения?");
            exitWithConfirmation();
        } else
            System.exit(0);
    }

    private void exitWithConfirmation() {
        System.out.println("Для подтверждения введите y, для ");
        if (in.nextLine().trim().toUpperCase().charAt(0) == 'Y')
            System.exit(0);
    }
}
