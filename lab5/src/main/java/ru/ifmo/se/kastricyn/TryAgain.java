package ru.ifmo.se.kastricyn;

public interface TryAgain {
    static void printErrors(boolean shouldPrintHints, RuntimeException e) {
        if (shouldPrintHints)
            System.out.println("Данные некорректны, повторите попытку:");
        else
            throw e;
    }
}
