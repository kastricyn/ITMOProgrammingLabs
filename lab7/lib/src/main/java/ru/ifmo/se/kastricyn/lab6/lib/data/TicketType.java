package ru.ifmo.se.kastricyn.lab6.lib.data;

import java.io.Serializable;

/**
 * Надо для {@link Ticket}
 */
public enum TicketType implements Serializable {
    USUAL,
    BUDGETARY,
    CHEAP;
}
