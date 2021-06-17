package ru.ifmo.se.kastricyn.lab7.server.db;

import org.jetbrains.annotations.NotNull;
import ru.ifmo.se.kastricyn.lab7.lib.data.Ticket;
import ru.ifmo.se.kastricyn.lab7.server.TicketCollection;

public interface DBTicketsI {
    /**
     * Создаёт необходимые таблицы, если они не существуют
     */
    void createTables();

    /**
     * Удаляет таблицы, если существуют
     * Используетя для создания своих "правильных"
     */
    void deleteTables();

    /**
     * Добавляет в БД билет
     * @param t билет, который надо добавить
     * @return id под которым был добавлен билет, -1 если билет не был добавлен
     */
    long addTicket(@NotNull Ticket t);
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
    @NotNull TicketCollection getTicketCollection();

    /**
     * Удаляет все элементы пользователя
     */
    boolean clear(long userId);
}
