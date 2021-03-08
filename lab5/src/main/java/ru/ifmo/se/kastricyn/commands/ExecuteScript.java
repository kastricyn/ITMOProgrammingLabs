package ru.ifmo.se.kastricyn.commands;

import ru.ifmo.se.kastricyn.CommandManager;
import ru.ifmo.se.kastricyn.TicketCollection;

import java.io.File;
import java.io.IOException;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.Stack;

public class ExecuteScript extends AbstractCommand {
    private TicketCollection ticketCollection;
    private static Stack<Path> openedScripts;

    static {
        openedScripts = new Stack<>();
    }

    public ExecuteScript(TicketCollection ticketCollection) {
        super("execute_script", "execute_script file_name \n - считать и исполнить скрипт из указанного файла. В скрипте содержатся команды в таком же виде, в котором их вводит пользователь в интерактивном режиме.");
        this.ticketCollection = ticketCollection;
    }

    @Override
    public void execute(String... args) {
        try{
        Path path = Paths.get(args[0]);
        try (Scanner scriptIn = new Scanner(path);){
//            Path path = Paths.get(args[0]);
            if (openedScripts.search(path) > -1) {
                System.out.println("Рекурсивное выполнение " + args[0] + " не поддерживается");
                return;
            }
            openedScripts.push(Paths.get(args[0]).toAbsolutePath());
//            Scanner scriptIn = new Scanner(path);
            CommandManager cm = CommandManager.getStandartCommandManager(ticketCollection, scriptIn, false);
            cm.run();
            openedScripts.pop();
        } catch (IOException e) {
            System.out.println("Не удалось получить доступ");
        }} catch (InvalidPathException e){
            System.out.println("Проверьте правильность пути и повторите попытку");

        }
    }
}
