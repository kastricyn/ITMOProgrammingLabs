package ru.ifmo.se.kastricyn;

import ru.ifmo.se.kastricyn.ticket.Ticket;

import java.lang.reflect.Field;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Comparator;
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

    public void update(long id, Ticket t) throws NoSuchFieldException, IllegalAccessException {
        setTicketId(removeById(id), -1);
        setTicketId(t, id);
        tickets.add(t);
// delete

//      sortById();
    }

    public Ticket removeById(long id) {
        Iterator<Ticket> iterator = tickets.iterator();
        while (iterator.hasNext()) {
            Ticket t = iterator.next();
            if (t.getId() == id) {
                iterator.remove();
                return t;
            }
// delete
//             else if (t.getId() > Ticket.getNextId())
//                throw new IllegalArgumentException("В коллекции нет элемента с таким id");
        }
        return null;
    }

    public void clear() {
        tickets.clear();
    }

    public Iterator<Ticket> iterator(){
        return tickets.iterator();
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
//            else if (t1.getId() - t2.getId() < 0)
//                return -1;
//            else return 0;
//        });
//    }

    private void setTicketId(Ticket t, long id) throws NoSuchFieldException, IllegalAccessException {
        Field fieldId = t.getClass().getDeclaredField("id");
        fieldId.setAccessible(true);
        fieldId.set(t, id);
    }

}
