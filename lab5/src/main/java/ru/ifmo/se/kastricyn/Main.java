package ru.ifmo.se.kastricyn;

import ru.ifmo.se.kastricyn.ticket.*;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.File;
import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        TicketCollection tickets = new TicketCollection();
        Scanner in = new Scanner(System.in);

        CommandManager consoleCommandManager = CommandManager.getStandartCommandManager(tickets, in, true);
        consoleCommandManager.run();

        Ticket t = new Ticket("name", new Coordinates(2l, 2.0f), 5, 2., TicketType.CHEAP,
                new Venue("ven", 45, VenueType.CINEMA, new Address("dfs")));

        try {
            JAXBContext context = JAXBContext.newInstance(Ticket.class);
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

            marshaller.marshal(t, new File("marsh"));
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }
}
