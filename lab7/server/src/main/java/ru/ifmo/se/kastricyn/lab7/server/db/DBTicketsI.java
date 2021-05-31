package ru.ifmo.se.kastricyn.lab7.server.db;

import com.sun.istack.internal.NotNull;
import ru.ifmo.se.kastricyn.lab7.lib.data.Ticket;
import ru.ifmo.se.kastricyn.lab7.server.TicketCollection;

public interface DBTicketsI {
    /**
     * Создаёт необходимые таблицы
     */
    void createTables();

    /**
     * Добавляет в БД билет
     * @param t билет, который надо добавить
     * @return true если добавлено, иначе false
     */
    boolean addTicket(@NotNull Ticket t);
    /**
     * Обновляет в БД билет
     * @param id номер билетв
     * @param t новый билет
     * @return true если добавлено, иначе false
     */
    boolean updateTicket(long id, @NotNull Ticket t);
    /**
     * Удаляет в БД билет
     * @param id номер билетв
     * @return true если удалено, иначе false
     */
    boolean deleteTicket(long id);

    /**
     * Возвращает коллецию билетов из БД.
     */
    @org.jetbrains.annotations.NotNull TicketCollection getTicketCollection();
}
