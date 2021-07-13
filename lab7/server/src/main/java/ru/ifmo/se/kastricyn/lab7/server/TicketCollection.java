package ru.ifmo.se.kastricyn.lab7.server;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.ifmo.se.kastricyn.lab7.lib.data.Ticket;
import ru.ifmo.se.kastricyn.lab7.server.db.DBManager;

import java.lang.reflect.Field;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Представляет коллекцию Ticket-ов
 */
public class TicketCollection implements Iterable<Ticket> {
    final static Logger log = LogManager.getLogger();

    private final @NotNull LocalDate initDate;
    @NotNull
    private final DBManager db;
    private volatile List<Ticket> tickets;

    /**
     * создаёт пустую коллекцию
     */

    public TicketCollection(@NotNull DBManager db) {
        tickets = Collections.synchronizedList(new LinkedList<>());
        initDate = LocalDate.now();
        this.db = db;
    }

    @NotNull
    public DBManager getDb() {
        return db;
    }

    /**
     * Добавляет элемент в коллекцию
     *
     * @param e элемент, который добавляют
     * @return true, если коллекция изменилась, иначе false
     */
    public boolean add(@NotNull Ticket e) {
        return tickets.add(e);
    }

    /**
     * Обновляёт (перезаписывает) элемент по id
     *
     * @param id        обновляемого (перезаписываемого / старого) элемена
     * @param newTicket обновлённый (новый) элемент
     * @throws NullPointerException     если <code>newTicket==null</code>
     * @throws IllegalArgumentException если в коллекции нет элемента с таким id
     */
    public void update(long id, @Nullable Ticket newTicket) throws NullPointerException, IllegalArgumentException {
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

    /**
     * Удаляет элемент из коллекции по id и возвращает его
     *
     * @param id id удаляемого элемента
     * @return удалённый элемент
     * @throws IllegalArgumentException если в коллекции не нашлось элемента с таким id
     */
    public Ticket remove(long id) throws IllegalArgumentException {
        synchronized (tickets) {
            Iterator<Ticket> iterator = tickets.iterator();
            Ticket t;
            while (iterator.hasNext()) {
                t = iterator.next();
                if (t.getId() == id) {
                    iterator.remove();
                    return t;
                }
            }
        }
        throw new IllegalArgumentException("В коллекции нет элемента с таким индексом");
    }

    /**
     * Удаляет из коллекции все элементы
     */
    public void clear() {
        tickets.clear();
    }

    /**
     * Возвращает iterator по коллекции
     */
    public @NotNull Iterator<Ticket> iterator() {
        return tickets.iterator();
    }

    /**
     * Возвращает элемент коллекции по его id
     *
     * @param id id элемента, который надо получить
     * @throws IllegalArgumentException если в коллекции не нашлось элемета с таким id
     */
    public Ticket getElement(long id) throws IllegalArgumentException {
        synchronized (tickets) {
            return tickets.stream().filter(x -> x.getId() == id).findAny().orElseThrow(
                    () -> new IllegalArgumentException("В коллекции нет элемента с таким индексом"));
        }
    }

    /**
     * Проверяет наличие элемента в колекции по его id
     *
     * @param id id элемента который проверяется
     * @return true, если элемент нашёлся, иначе false
     */
    public boolean hasElement(long id) {
        return tickets.stream().anyMatch(x -> x.getId() == id);
    }

    /**
     * Возвращает первый элемент, но не удаляет его
     */
    public Ticket peekFirst() {
        return tickets.get(0);
    }

    /**
     * Возвращает кол-во элементов в коллекции
     *
     * @return число типа int, равное кол-ву элементов в коллекции
     */
    public int size() {
        return tickets.size();
    }

    /**
     * Проверяет пустая ли коллекция
     *
     * @return true, если кол-во элементов равно 0, иначе false
     */
    public boolean isEmpty() {
        return tickets.isEmpty();
    }

    /**
     * Сортирует коллекцию по умолчанию
     */
    public @NotNull TicketCollection sort() {
        sort(Ticket::compareTo);
        return this;
    }

    /**
     * Сортирует коллекцию
     *
     * @param cmp компаратор по которому будет проходит сортировка
     */
    public @NotNull TicketCollection sort(Comparator<Ticket> cmp) {
        tickets.sort(cmp);
        return this;
    }


    /**
     * Удаляет элементы коллекции пользователя
     *
     * @param userId id пользователя
     */
    public void clear(long userId) {
        tickets =
                Collections.synchronizedList(tickets.stream().filter(x -> x.getUserId() != userId).collect(Collectors.toList()));
    }

    /**
     * Возвращает дату инициализации коллекции
     */
    public @NotNull LocalDate getInitDate() {
        return initDate;
    }

    /**
     * Проверяет коллекцию. Используется после восстановления коллекции из хранилища.
     * Удаляет элементы, которые были изменены в файле за допустимые пределы.
     * Возвращает true если что-то было удалено, иначе false.
     */
    public boolean check() {
        HashSet<Long> idTicket = new HashSet<>();
        HashSet<Long> idVenue = new HashSet<>();
        synchronized (tickets) {
            Iterator<Ticket> iterator = iterator();
            boolean isDeleted = false;
            while (iterator.hasNext()) {
                Ticket t = iterator.next();
                try {
                    t.isExisting();
                    long idT = t.getId();
                    long idV = t.getVenue().getId();
                    if (idTicket.contains(idT) || idVenue.contains(idV)) {
                        iterator.remove();
                        isDeleted = true;
                        continue;
                    }
                    idTicket.add(idT);
                    idVenue.add(idV);
                } catch (RuntimeException e) {
                    iterator.remove();
                    isDeleted = true;
                }
            }
            if (isDeleted) {
                return true;
            }
            try {
                Field nextId = Ticket.class.getDeclaredField("nextId");
                nextId.setAccessible(true);
                nextId.setLong(Ticket.class, idTicket.stream().max(Long::compareTo).orElse(0L) + 1);
            } catch (@NotNull NoSuchFieldException | IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

}
