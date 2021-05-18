package ru.ifmo.se.kastricyn.lab5.commands;

import ru.ifmo.se.kastricyn.lab5.TicketCollection;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;

/**
 * Команда сохранить коллекцию в файл, путь до которого передан при запуске программы
 */
public class Save extends AbstractCommand {
    TicketCollection ticketCollection;

    public Save(TicketCollection ticketCollection) {
        super("save", "save \n - сохранить коллекцию в файл");
        this.ticketCollection = ticketCollection;
    }

    @Override
    public void execute(String... args) {
        boolean flag = ticketCollection.isSaved();
        try {
            ticketCollection.setSaved(true);
            JAXBContext context = JAXBContext.newInstance(TicketCollection.class);
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            marshaller.marshal(ticketCollection, ticketCollection.getFile());
            System.out.println("Сохранено.");
        } catch (Exception e) {
            System.out.println("Сохранить не удалось.");
            ticketCollection.setSaved(flag);
        }
    }
}
