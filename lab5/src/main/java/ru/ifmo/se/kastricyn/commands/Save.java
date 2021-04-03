package ru.ifmo.se.kastricyn.commands;

import ru.ifmo.se.kastricyn.CollectionState;
import ru.ifmo.se.kastricyn.TicketCollection;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import java.io.File;

public class Save extends AbstractCommand{
    TicketCollection ticketCollection;

    public Save(TicketCollection ticketCollection) {
        super("save","save \n - сохранить коллекцию в файл");
        this.ticketCollection = ticketCollection;
    }

    @Override
    public void execute(String... args) {
        try {
        JAXBContext context = JAXBContext.newInstance(TicketCollection.class);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        marshaller.marshal(ticketCollection, ticketCollection.getFile());

        } catch (Exception e){
            e.printStackTrace();
        }
       ticketCollection.setState(CollectionState.SAVED);
    }
}
