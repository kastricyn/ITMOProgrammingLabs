package ru.ifmo.se.kastricyn.lab6.server;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.ifmo.se.kastricyn.lab6.lib.LocalDateAdapter;
import ru.ifmo.se.kastricyn.lab6.lib.data.Ticket;

import javax.xml.bind.annotation.*;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.ArrayDeque;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.stream.Collectors;

/**
 * Представляет коллекцию Ticket-ов
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class TicketCollection implements Iterable<Ticket> {
    @XmlTransient
    final static Logger log = LogManager.getLogger();

    @XmlJavaTypeAdapter(value = LocalDateAdapter.class)
    @XmlAttribute
    private final LocalDate initDate;
    @XmlTransient
    private boolean saved;
    private ArrayDeque<Ticket> tickets;
    @XmlTransient
    private Path path;

    /**
     * создаёт пустую коллекцию
     */
    public TicketCollection() {
        tickets = new ArrayDeque<>();
        saved = true;
        initDate = LocalDate.now();
    }

    public TicketCollection(Path p) {
        tickets = new ArrayDeque<>();
        saved = true;
        initDate = LocalDate.now();
        this.path = p;
    }

    /**
     * Добавляет элемент в коллекцию
     *
     * @param e элемент, который добавляют
     * @return true, если коллекция изменилась, иначе false
     */
    public boolean add(Ticket e) {
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
    public void update(long id, Ticket newTicket) throws NullPointerException, IllegalArgumentException {
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

    /**
     * Удаляет из коллекции все элементы
     */
    public void clear() {
        tickets.clear();
    }

    /**
     * Возвращает iterator по коллекции
     */
    public Iterator<Ticket> iterator() {
        return tickets.iterator();
    }

    /**
     * Возвращает элемент коллекции по его id
     *
     * @param id id элемента, который надо получить
     * @throws IllegalArgumentException если в коллекции не нашлось элемета с таким id
     */
    public Ticket getElement(long id) throws IllegalArgumentException {
        return tickets.stream().filter(x -> x.getId() == id).findAny().orElseThrow(
                () -> new IllegalArgumentException("В коллекции нет элемента с таким индексом"));
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
        return tickets.peekFirst();
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
    public TicketCollection sort() {
        sort(Ticket::compareTo);
        return this;
    }

    /**
     * Сортирует коллекцию
     *
     * @param cmp компаратор по которому будет проходит сортировка
     */
    //todo: why is it working
    public TicketCollection sort(Comparator<Ticket> cmp) {
        tickets = tickets.stream().sorted(cmp).collect(Collectors.toCollection(ArrayDeque::new));
        return this;
    }

    /**
     * Возвращает файл, связанный с коллекцией
     */
    public Path getPath() {
        return path;
    }

    /**
     * устанавливает путь по которому будет сохраняться коллекция
     */
    public TicketCollection setPath(Path path) {
        this.path = path;
        return this;
    }

    /**
     * Возвращает дату инициализации коллекции
     */
    public LocalDate getInitDate() {
        return initDate;
    }

    /**
     * Проверяет сохранена ли коллекция в файл
     *
     * @return true, если сохранена, иначе false
     */
    public boolean isSaved() {
        return saved;
    }

    /**
     * Устанавливает параметр сохранения коллекции
     *
     * @param saved true - считать коллекцию сохранённой, иначе false
     */
    public void setSaved(boolean saved) {
        this.saved = saved;
    }

    /**
     * Проверяет коллекцию. Используется после восстановления коллекции из файла.
     * Удаляет элементы, которые были изменены в файле за допустимые пределы.
     */
    public void check() {
        HashSet<Long> idTicket = new HashSet<>();
        HashSet<Long> idVenue = new HashSet<>();
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
        if (isDeleted){
            System.out.println("Некоторые элементы не были импортированы, из-за неверных данных");
            log.warn("Некоторые элементы не были импортированы, из-за неверных данных");
        }
    }
}
