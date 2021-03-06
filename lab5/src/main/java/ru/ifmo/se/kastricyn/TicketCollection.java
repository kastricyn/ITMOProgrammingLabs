package ru.ifmo.se.kastricyn;

import ru.ifmo.se.kastricyn.ticket.Ticket;

import java.util.ArrayDeque;
import java.util.Iterator;

public class TicketCollection {
    private ArrayDeque<Ticket> tickets;

    public TicketCollection() {
        tickets = new ArrayDeque<>();
    }

    public TicketCollection(int numElements) {
        tickets = new ArrayDeque<>(numElements);
    }

    public boolean add(Ticket e) {
        return tickets.add(e);
    }

    public void updateElement(long id, Ticket t) {
        if (t == null)
            throw new NullPointerException();
        Ticket tmp = getElement(id);
        tmp.setName(t.getName());
        tmp.setVenue(t.getVenue());
        tmp.setType(t.getType());
        tmp.setDiscount(t.getDiscount());
        tmp.setPrice(t.getPrice());
        tmp.setCoordinates(t.getCoordinates());
    }

    public Ticket removeElement(long id) {
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

    public Ticket peekFirst() {
        return tickets.peekFirst();
    }

//  delete

//    private void sort(Comparator<Ticket> cmp) {
//        Ticket[] sortedTickets = tickets.toArray(new Ticket[0]);
//        Arrays.sort(sortedTickets, cmp);
//        tickets = new ArrayDeque<>(Arrays.asList(sortedTickets));
//    }

//    private void sortById() {
//        sort((t1, t2) -> {
//            if (t1.getId() - t2.getId() > 0)
//                return 1;
//             else if (t1.getId() - t2.getId() < 0)
//                return -1;
//            else return 0;
//        });
//    }


}
