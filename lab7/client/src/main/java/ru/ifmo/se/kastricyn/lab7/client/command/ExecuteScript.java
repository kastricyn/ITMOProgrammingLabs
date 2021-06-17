package ru.ifmo.se.kastricyn.lab7.client.command;

import org.jetbrains.annotations.NotNull;
import ru.ifmo.se.kastricyn.lab7.client.ClientAbstractCommand;
import ru.ifmo.se.kastricyn.lab7.client.ClientCommandManager;
import ru.ifmo.se.kastricyn.lab7.lib.utility.Console;

import java.io.IOException;
import java.net.SocketException;
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
public class ExecuteScript extends ClientAbstractCommand {
    private static final Stack<Path> openedScripts = new Stack<>();

    public ExecuteScript() {
        super("execute_script", "считать и исполнить скрипт из указанного файла. В скрипте содержатся команды в таком же виде, в котором их вводит пользователь в интерактивном режиме.");
        setNeedArgumentType(ClientCommandManager.class);
    }

    @Override
    public void execute(String @NotNull ... args) {
        assert objArgs != null;
        if(objArgs.getCommandManager().getUser()==null){
            answer = "Необходимо авторизоваться";
            return;
        }
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
        if (openedScripts.search(path.toAbsolutePath()) > -1) {
            answer = "Рекурсивное выполнение " + args[0] + " не поддерживается";
            return;
        }

        try (Scanner scriptIn = new Scanner(path)) {
            assert objArgs != null;
            ClientCommandManager ccm = objArgs.getCommandManager();

            openedScripts.push(Paths.get(args[0]).toAbsolutePath());
            ClientCommandManager cm = ClientCommandManager.getStandards(ccm.getConnection(), new Console(scriptIn,
                    false));
            cm.setUser(ccm.getUser());
            //todo проверить new commandManager для клиента и сервера разные
            cm.run();

            openedScripts.pop();
        } catch (SocketException e) {
            answer = "Соединение утеряно, попробуйте перезапустить программу.";
        } catch (IOException e) {
            answer = "Не удалось прочитать файл.";
        } catch (Exception e) {
            answer = "Выполнение скрипта не удалось завершить правильно.";
            e.printStackTrace();
        }
    }
}
