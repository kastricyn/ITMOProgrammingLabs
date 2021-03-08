package ru.ifmo.se.kastricyn;

import ru.ifmo.se.kastricyn.commands.Help;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        TicketCollection tickets = new TicketCollection();
        Scanner in = new Scanner(System.in);

        CommandManager consoleCommandManager = CommandManager.getStandartCommandManager(tickets, in, true);
        consoleCommandManager.run();
    }
}
