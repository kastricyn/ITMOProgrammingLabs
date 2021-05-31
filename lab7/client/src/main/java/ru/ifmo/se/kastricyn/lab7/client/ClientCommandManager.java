package ru.ifmo.se.kastricyn.lab7.client;

import org.jetbrains.annotations.NotNull;
import ru.ifmo.se.kastricyn.lab7.client.command.ExecuteScript;
import ru.ifmo.se.kastricyn.lab7.client.command.Exit;
import ru.ifmo.se.kastricyn.lab7.client.command.Help;
import ru.ifmo.se.kastricyn.lab7.lib.CommandManager;
import ru.ifmo.se.kastricyn.lab7.lib.User;
import ru.ifmo.se.kastricyn.lab7.lib.connection.CommandArgument;
import ru.ifmo.se.kastricyn.lab7.lib.connection.ServerAnswer;
import ru.ifmo.se.kastricyn.lab7.lib.connection.ServerRequest;
import ru.ifmo.se.kastricyn.lab7.lib.data.Ticket;
import ru.ifmo.se.kastricyn.lab7.lib.data.Venue;
import ru.ifmo.se.kastricyn.lab7.lib.utility.Console;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.net.SocketException;
import java.util.Arrays;

public class ClientCommandManager extends CommandManager {
    private Console console;
    private Connection connection;
    private User user;

    public ClientCommandManager(Connection connection, Console console) {
        this.connection = connection;
        this.console = console;
    }

    public static @NotNull ClientCommandManager getStandards(Connection connection, Console console) {
        ClientCommandManager ccm = new ClientCommandManager(connection, console);
        ccm.addIfAbsent(new Exit());
        ccm.addIfAbsent(new Help());
        ccm.addIfAbsent(new ExecuteScript());
        return ccm;
    }

    public User getUser() {
        return user;
    }

    public ClientCommandManager setUser(User user) {
        this.user = user;
        return this;
    }

    public Connection getConnection() {
        return connection;
    }

    /**
     * Исполняет команду, имя которой передано в первом аргументе, если она доступна в менеджере команд
     *
     * @param commandName что ввёл пользователь
     * @param args        аргументы команды в строковом представлении
     */
    public void executeCommand(@NotNull String commandName, String @NotNull ... args) throws JAXBException, IOException {

        String input = commandName;
        StringBuilder inputBuilder = new StringBuilder(commandName);
        for (String a :
                args) {
            inputBuilder.append(" ").append(a);
        }
        input = inputBuilder.toString();

        ClientAbstractCommand command = (ClientAbstractCommand) getCommand(commandName);
        if (command == null) {
            ServerRequest sr = new ServerRequest(input).setObjArgs(new CommandArgument().setUser(user));
            while (true) {
                ServerAnswer sa = connection.getAnswer(sr);
                switch (sa.getSat()) {
                    case OK:
                        console.println(sa.getAnswer());
                        return;
                    case NOT_FOUND_COMMAND:
                        console.printlnErr("Команды " + commandName + " не существует. Для вызова справки введите: help");
                        return;
                    case NEED_ARGS:
                        console.printHints("Для этой команды необходимы дополнительные аргументы.");
                        CommandArgument cca = new CommandArgument().setUser(user);
                        for (Class eClass :
                                sa.getArgTypes()) {
                            if (eClass == null)
                                continue;
                            else if (eClass.equals(Ticket.class))
                                cca.setTicket(new Ticket(console));
                            else if (eClass.equals(Venue.class))
                                cca.setVenue(new Venue(console));
                            else if (eClass.equals(User.class))
                                cca.setUser(new User(console));
                        }
                        sr.setObjArgs(cca);
                        break;
                    default:
                        console.printlnErr("Какой-то новый ServerAnswerType");
                        return;
                }
            }

        }
        //установка аргументов команде
        ClientCommandArgument cca = new ClientCommandArgument();
        for (Class eClass : command.getArgumentTypes()) {
            if (eClass.equals(Ticket.class))
                cca.setTicket(new Ticket(console));
            else if (eClass.equals(Venue.class))
                cca.setVenue(new Venue(console));
            else if (eClass.isInstance(this))
                cca.setCommandManager(this);
            else if (eClass.equals(User.class))
                cca.setUser(new User(console));
        }
        command.setArguments(cca);
        command.execute(args);
        console.println(command.getAnswer());
    }

    /**
     * Обрабатывает команды, пока они поступают от пользователя.
     * Надо переопределить для конкретной реализации
     */
    @Override
    public void run() {
        while (isWorkable() && console.hasNext()) {
            String t = console.nextLine().trim();
            if (t.isEmpty())
                continue;
            String[] s = t.split("\\s", 2);
            try {
                executeCommand(s[0], Arrays.copyOfRange(s, 1, s.length));
            } catch (@NotNull SocketException | StringIndexOutOfBoundsException e) {
                console.println("Соеденение утеряно, запустите программу заново");
                e.printStackTrace();
//                return; debug
            } catch (@NotNull JAXBException | IOException e) {
                e.printStackTrace();
            }
        }
    }

}
