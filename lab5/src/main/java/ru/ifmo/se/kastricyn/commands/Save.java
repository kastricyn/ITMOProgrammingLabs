package ru.ifmo.se.kastricyn.commands;

import ru.ifmo.se.kastricyn.TicketCollection;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;

public class Save extends AbstractCommand {
    TicketCollection ticketCollection;

    public Save(TicketCollection ticketCollection) {
        super("save", "save \n - сохранить коллекцию в файл");
        this.ticketCollection = ticketCollection;
    }

    @Override
    public void execute(String... args) {
        try {
            ticketCollection.setSaved(true);
            JAXBContext context = JAXBContext.newInstance(TicketCollection.class);
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            marshaller.marshal(ticketCollection, ticketCollection.getFile());
            System.out.println("Сохранено.");
        } catch (Exception e) {
            System.out.println("Сохранить не удалось.");
            ticketCollection.setSaved(false);
        }
    }
}
