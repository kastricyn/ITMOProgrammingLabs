package ru.ifmo.se.kastricyn.lab6.server.commands;

import ru.ifmo.se.kastricyn.lab6.lib.AbstractCommand;
import ru.ifmo.se.kastricyn.lab6.lib.AbstractCommandManager;
import ru.ifmo.se.kastricyn.lab6.lib.utility.Console;
import ru.ifmo.se.kastricyn.lab6.server.TicketCollection;
import ru.ifmo.se.kastricyn.lab6.server.commandManager.ConsoleCommandManager;

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
    private static Stack<Path> openedScripts;

    static {
        openedScripts = new Stack<>();
    }

    public ExecuteScript() {
        super("execute_script", "считать и исполнить скрипт из указанного файла. В скрипте содержатся команды в таком же виде, в котором их вводит пользователь в интерактивном режиме.");
        setArgumentTypes(TicketCollection.class);
    }

    //TODO: загрузка скриптов с клиента (на клиенте обрабатывается команда execute_script, и каждая команда, которой нет
    // на клиенте отправляется на сервер

    @Override
    public void execute(String... args) {
        TicketCollection ticketCollection = (TicketCollection) this.args.get(0);

        if (args.length != 1) {
            answer = "Данная команда должна принимать один аргумет - путь до файла.";
            return;
        }

        Path path = Paths.get(args[0]);
        if (Files.notExists(path)) {
            answer = "Файл не найден.";
            return;
        }
        if (!Files.isReadable(path)) {
            answer = "Недостаточно прав для чтения";
            return;
        }

        try (Scanner scriptIn = new Scanner(path)) {
            if (openedScripts.search(path.toAbsolutePath()) > -1) {
                answer = "Рекурсивное выполнение " + args[0] + " не поддерживается";
                return;
            }
            openedScripts.push(Paths.get(args[0]).toAbsolutePath());
            AbstractCommandManager cm = ConsoleCommandManager.getStandards(ticketCollection, new Console(scriptIn, false));
            //todo проверить new commandManager для клиента и сервера разные
            cm.run();
            openedScripts.pop();
        } catch (IOException e) {
            answer = "Не удалось прочитать файл.";
        } catch (Exception e) {
            answer = "Выполнение скрипта не удалось завершить правильно.";
        }
    }
}
