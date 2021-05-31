package ru.ifmo.se.kastricyn.lab7.lib.data;

import java.io.Serializable;

/**
 * Надо для {@link Ticket}
 */
public enum TicketType implements Serializable {
    USUAL,
    BUDGETARY,
    CHEAP;
}
