package ru.ifmo.se.kastricyn.lab7.server.commands;

import ru.ifmo.se.kastricyn.lab7.lib.User;
import ru.ifmo.se.kastricyn.lab7.lib.data.Ticket;
import ru.ifmo.se.kastricyn.lab7.server.TicketCollection;

import java.util.Comparator;
import java.util.Optional;
import java.util.stream.StreamSupport;

/**
 * Команда добавить элемент в колекцию, если он больше всех элементов в колекции
 */
public class AddIfMax extends CommandWithAuth {

    public AddIfMax() {
        super("add_if_max", "добавить новый элемент в коллекцию, если его значение превышает значение наибольшего элемента этой коллекции");

        setNeedArgumentType(TicketCollection.class, Ticket.class, User.class);
    }


    @Override
    public void execute(String... args) {
        if(!auth())
            return;
        assert objArgs != null;
        TicketCollection ticketCollection = objArgs.getTicketCollection();
        Ticket maxTicket = objArgs.getTicket().setUserId(user.getId());
        Optional<Ticket> t = StreamSupport.stream(ticketCollection.spliterator(), true).max(Comparator.naturalOrder());
        if (t.isPresent())
            if (t.get().compareTo(maxTicket) < 0) {
                if(ticketCollection.getDb().addTicket(maxTicket)==-1){
                    answer = "Добавление не удалось";
                    return;
                }
                ticketCollection.add(maxTicket);
                answer = "В коллекцю добавлен объект " + t.toString();
            } else
                answer = "Коллекция не изменена (данный элемент не больше наибольшего).";
        else
            answer = "Коллекция пуста (=> наибольшего эллемента нет), используйте команду add.";

        ticketCollection.setSaved(false);
    }
}
