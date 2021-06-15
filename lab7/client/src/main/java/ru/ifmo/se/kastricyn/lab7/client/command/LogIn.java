package ru.ifmo.se.kastricyn.lab7.client.command;

import org.jetbrains.annotations.NotNull;
import ru.ifmo.se.kastricyn.lab7.client.ClientAbstractCommand;
import ru.ifmo.se.kastricyn.lab7.client.ClientCommandManager;
import ru.ifmo.se.kastricyn.lab7.lib.User;
import ru.ifmo.se.kastricyn.lab7.lib.connection.ServerAnswer;
import ru.ifmo.se.kastricyn.lab7.lib.connection.ServerRequest;
import ru.ifmo.se.kastricyn.lab7.lib.utility.NotNeedAuth;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.net.SocketException;

public class LogIn extends ClientAbstractCommand implements NotNeedAuth {
    public LogIn() {
        super("log_in", "войти");
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
            ServerAnswer sa = ccm.getConnection().getAnswer(new ServerRequest("log_in").setObjArgs(objArgs));
            //todo delete костыль, отправлять статус отдельно от ответа пользователю
            assert sa != null;
            if (sa.getAnswer().contains("Вы авторизованы"))
                ccm.setUser(objArgs.getUser());
            answer = sa.getAnswer();
        } catch (SocketException e) {
            answer = "Соединение утеряно, попробуйте перезапустить программу.";
        } catch (@NotNull IOException | JAXBException e) {
            e.printStackTrace();
        }
    }
}
