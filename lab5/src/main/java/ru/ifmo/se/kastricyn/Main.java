package ru.ifmo.se.kastricyn;

import ru.ifmo.se.kastricyn.ticket.*;

import javax.xml.bind.JAXBException;
import java.nio.file.AccessDeniedException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        TicketCollection tickets = null;
        try {
            tickets = TicketCollection.createTicketCollection(Paths.get(args[0]));
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (JAXBException e) {
            e.printStackTrace();
        } catch (AccessDeniedException e) {
            e.printStackTrace();
        }
        Scanner in = new Scanner(System.in);



        Ticket t = new Ticket("name", new Coordinates(2l, 2.0f), 5, 2., TicketType.CHEAP,
                new Venue("ven", 45, VenueType.CINEMA, new Address("dfs")));

        tickets.add(t);
        CommandManager consoleCommandManager = CommandManager.createCommandManager(tickets, in, true);
        consoleCommandManager.run();
    }
}
