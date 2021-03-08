package ru.ifmo.se.kastricyn.commands;

public class Exit extends AbstractCommand{
    public Exit() {
        super("exit", "exit \n - завершить программу (без сохранения в файл)");
    }

    @Override
    public void execute(String... args) {
        System.exit(0);
    }
}
