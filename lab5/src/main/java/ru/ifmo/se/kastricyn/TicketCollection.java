package ru.ifmo.se.kastricyn;

import ru.ifmo.se.kastricyn.commands.Save;
import ru.ifmo.se.kastricyn.data.Ticket;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.*;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.io.File;
import java.io.IOException;
import java.nio.file.AccessDeniedException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.*;
import java.util.function.Consumer;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class TicketCollection {
    @XmlJavaTypeAdapter(value = LocalDateAdapter.class)
    @XmlAttribute
    private LocalDate initDate;
    @XmlAttribute
    private boolean saved;
    private ArrayDeque<Ticket> tickets;

    @XmlTransient
    private File file;

    private TicketCollection() {
        tickets = new ArrayDeque<>();
        saved = true;
        initDate = LocalDate.now();
    }

    public static TicketCollection getTicketCollection(Path p) throws JAXBException, AccessDeniedException {
            if(!Files.isReadable(p))
                throw new AccessDeniedException(p.toString());
            JAXBContext context = JAXBContext.newInstance(TicketCollection.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            TicketCollection ticketCollection = (TicketCollection) unmarshaller.unmarshal(p.toFile());
            ticketCollection.file = p.toFile();
            ticketCollection.saved = false;
            return ticketCollection;
    }

    public static TicketCollection createTicketCollection(Path p) {
        TicketCollection ticketCollection =  new TicketCollection();
        ticketCollection.file = p.toFile();
        try {
            Files.createFile(p);
        } catch (IOException e) {
            System.out.println("Создать файл не удалось. Сохранение не доступно.");
        }
        if(Files.isWritable(p)){
            new Save(ticketCollection).execute();
        }
        return ticketCollection;
    }

    public boolean add(Ticket e) {
        return tickets.add(e);
    }

    public void update(long id, Ticket newTicket) {
        if (newTicket == null)
            throw new NullPointerException();
        Ticket tmp = getElement(id);
        tmp.setName(newTicket.getName());
        tmp.setVenue(newTicket.getVenue());
        tmp.setType(newTicket.getType());
        tmp.setDiscount(newTicket.getDiscount());
        tmp.setPrice(newTicket.getPrice());
        tmp.setCoordinates(newTicket.getCoordinates());
    }

    public Ticket remove(long id) {
        Iterator<Ticket> iterator = tickets.iterator();
        Ticket t;
        while (iterator.hasNext()) {
            t = iterator.next();
            if (t.getId() == id) {
                iterator.remove();
                return t;
            }
        }
        throw new IllegalArgumentException("В коллекции нет элемента с таким индексом");
    }

    public void clear() {
        tickets.clear();
    }

    public Iterator<Ticket> iterator() {
        return tickets.iterator();
    }

    public Ticket getElement(long id) {
        for (Ticket t : tickets) {
            if (t.getId() == id)
                return t;
        }
        throw new IllegalArgumentException("В коллекции нет элемента с таким индексом");
    }

    public boolean hasElement(long id){
        for (Ticket t : tickets) {
            if (t.getId() == id)
                return true;
        }
        return false;
    }

    public Ticket peekFirst() {
        return tickets.peekFirst();
    }

    public int size() {
        return tickets.size();
    }

    public boolean isEmpty() {
        return tickets.isEmpty();
    }

    public void forEach(Consumer<? super Ticket> action) {
        tickets.forEach(action);
    }


//  delete

    public void sort() {
        sort(Ticket::compareTo);
    }

    //todo: why is it working
    public void sort(Comparator<Ticket> cmp) {
        LinkedList<Ticket> list = new LinkedList<>(tickets);
        Collections.sort(list, cmp);
        tickets = new ArrayDeque<>(list);
    }


    public File getFile() {
        return file;
    }

    public LocalDate getInitDate() {
        return initDate;
    }

    public boolean isSaved() {
        return saved;
    }

    public void setSaved(boolean saved) {
        this.saved = saved;
    }
}
