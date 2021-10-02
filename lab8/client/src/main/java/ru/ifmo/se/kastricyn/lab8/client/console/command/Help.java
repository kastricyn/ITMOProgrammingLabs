package ru.ifmo.se.kastricyn.lab8.client.console.command;

import ru.ifmo.se.kastricyn.lab8.client.console.ClientAbstractCommand;
import ru.ifmo.se.kastricyn.lab8.client.console.ClientCommandManager;
import ru.ifmo.se.kastricyn.lab8.lib.AbstractCommand;
import ru.ifmo.se.kastricyn.lab8.lib.User;
import ru.ifmo.se.kastricyn.lab8.lib.connection.CommandArgument;
import ru.ifmo.se.kastricyn.lab8.lib.connection.ServerAnswer;
import ru.ifmo.se.kastricyn.lab8.lib.connection.ServerRequest;
import ru.ifmo.se.kastricyn.lab8.lib.utility.Console;
import ru.ifmo.se.kastricyn.lab8.lib.utility.NotNeedAuth;

import java.io.IOException;
import java.net.SocketException;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

public class Help extends ClientAbstractCommand implements NotNeedAuth {
    /**
     * конструктор класса наседника, принимает на вход параметры, необходимые для реализации конкретной команды
     * вызвается из конструктора класса наследника
     */
    public Help() {
        super("help", "вывести справку по доступным командам");
        setNeedArgumentType(ClientCommandManager.class);
    }

    /**
     * @param args аргументы команды
     * @throws IndexOutOfBoundsException if paramsIsValidate()!=true
     */
    @Override
    public void execute(String... args) {
        assert objArgs != null;
        ClientCommandManager ccm = objArgs.getCommandManager();

        try {
            //получаем доступные команды от сервера
            ServerAnswer sa =
                    ccm.getConnection().getAnswer(new ServerRequest("help").setObjArgs(new CommandArgument().setUser(ccm.getUser() == null ? new User(-1, "") : ccm.getUser())));
            assert sa != null;
            String string = sa.getAnswer();

            // выделяем команды как строки в Set
            final String regex = "^([\\w\\{}\\s-]{2,})([А-Яа-я ,\\.()\\w]+)$";
            final Pattern pattern = Pattern.compile(regex, Pattern.MULTILINE);
            final Matcher matcher = pattern.matcher(string);
            Set<String> strings = new HashSet<>();
            while (matcher.find())
                strings.add(matcher.group(0));

            answer = Console.getStringFromStream("Доступны следующие команды:",
                    Stream.concat(strings.stream(),
                            ccm.getCommands().filter(x -> ccm.getUser() != null || x instanceof NotNeedAuth).map(AbstractCommand::toString)).distinct().sorted());
        } catch (SocketException e) {
            answer = "Соединение утеряно, попробуйте перезапустить программу.";
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
