package ru.ifmo.se.kastricyn.lab6.server.commands;

import ru.ifmo.se.kastricyn.lab6.server.CommandManager;
import ru.ifmo.se.kastricyn.lab6.lib.Console;
import ru.ifmo.se.kastricyn.lab6.server.TicketCollection;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.Stack;

/**
 * Команда исполнить скрипт
 * путь до скрипта должен передаваться как аргумент команды
 * Рекцрсивное выполнение скриптов не поддерживается
 */
public class ExecuteScript extends AbstractCommand {
    private TicketCollection ticketCollection;
    private static Stack<Path> openedScripts;

    static {
        openedScripts = new Stack<>();
    }

    public ExecuteScript(TicketCollection ticketCollection) {
        super("execute_script", "считать и исполнить скрипт из указанного файла. В скрипте содержатся команды в таком же виде, в котором их вводит пользователь в интерактивном режиме.");
        this.ticketCollection = ticketCollection;
    }

    @Override
    public void execute(String... args) {
        if (args.length != 1) {
            System.out.println("Данная команда должна принимать один аргумет - путь до файла.");
            return;
        }

        Path path = Paths.get(args[0]);
        if (Files.notExists(path)) {
            System.out.println("Файл не найден.");
            return;
        }
        if (!Files.isReadable(path)) {
            System.out.println("Недостаточно прав для чтения");
            return;
        }

        try (Scanner scriptIn = new Scanner(path)) {
            if (openedScripts.search(path.toAbsolutePath()) > -1) {
                System.out.println("Рекурсивное выполнение " + args[0] + " не поддерживается");
                return;
            }
            openedScripts.push(Paths.get(args[0]).toAbsolutePath());
            CommandManager cm = CommandManager.createCommandManager(ticketCollection, new Console(scriptIn, false));
            cm.run();
            openedScripts.pop();
        } catch (IOException e) {
            System.out.println("Что-то пошло не так. Не удалось прочитать файл.");
        } catch (Exception e) {
            System.err.println("Выполнение скрипта не удалось завершить правильно.");
        }
    }
}
