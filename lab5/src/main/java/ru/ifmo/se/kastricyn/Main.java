package ru.ifmo.se.kastricyn;

import ru.ifmo.se.kastricyn.ticket.Ticket;
import ru.ifmo.se.kastricyn.ticket.TicketType;

import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        String str = Arrays.toString(TicketType.values());
        str = str.substring(1, str.length()-1);
        System.out.println("Введите поле type (возможны следующие варианты: " + str + " ):\n");
    }
}
