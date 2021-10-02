package ru.ifmo.se.kastricyn.lab8.client.console.command;

import ru.ifmo.se.kastricyn.lab8.client.console.ClientAbstractCommand;
import ru.ifmo.se.kastricyn.lab8.client.console.ClientCommandManager;
import ru.ifmo.se.kastricyn.lab8.lib.User;
import ru.ifmo.se.kastricyn.lab8.lib.connection.CommandArgument;
import ru.ifmo.se.kastricyn.lab8.lib.connection.ServerAnswer;
import ru.ifmo.se.kastricyn.lab8.lib.connection.ServerRequest;
import ru.ifmo.se.kastricyn.lab8.lib.utility.NotNeedAuth;

import java.io.IOException;
import java.net.SocketException;

public class LogIn extends ClientAbstractCommand implements NotNeedAuth {
    public LogIn() {
        super("log_in", "войти/сменить пользователя");
        setNeedArgumentType(ClientCommandManager.class, User.class);
    }

    /**
     * @param args аргументы команды
     */
    @Override
    public void execute(String... args) {
        assert objArgs != null;
        ClientCommandManager ccm = objArgs.getCommandManager();
        try {
            ServerAnswer sa =
                    ccm.getConnection().getAnswer(new ServerRequest("log_in").setObjArgs(new CommandArgument().setUser(objArgs.getUser())));
            //todo delete костыль, отправлять статус отдельно от ответа пользователю
            assert sa != null;
            if (sa.getAnswer().contains("Вы авторизованы"))
                ccm.setUser(objArgs.getUser());
            answer = sa.getAnswer();
        } catch (SocketException e) {
            answer = "Соединение утеряно, попробуйте перезапустить программу.";
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
