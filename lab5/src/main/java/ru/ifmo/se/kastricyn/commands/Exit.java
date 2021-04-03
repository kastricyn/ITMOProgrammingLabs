package ru.ifmo.se.kastricyn.commands;

import ru.ifmo.se.kastricyn.CollectionState;
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
        switch (ticketCollection.getState()) {
            case JUST_CREATED:
                System.out.println("Коллекция была только что создана, но не сохранена в файл, вы уверены, что хотите выйти?");
                exitWithConfirmation();
                break;
            case EDITED:
                System.out.println("Коллекция была изменена после последнего сохранения, вы уверены, что хотите выйти?");
                exitWithConfirmation();
                break;
            case SAVED:
                System.exit(0);
        }


    }

    private void exitWithConfirmation() {
        char answ = in.nextLine().trim().charAt(0);
        if (answ == 'y' || answ == 'Y')
            System.exit(0);
    }
}
