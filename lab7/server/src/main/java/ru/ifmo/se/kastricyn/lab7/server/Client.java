package ru.ifmo.se.kastricyn.lab7.server;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jetbrains.annotations.NotNull;
import ru.ifmo.se.kastricyn.lab7.lib.CommandManager;
import ru.ifmo.se.kastricyn.lab7.lib.connection.ServerAnswer;
import ru.ifmo.se.kastricyn.lab7.lib.connection.ServerAnswerType;
import ru.ifmo.se.kastricyn.lab7.lib.connection.ServerRequest;
import ru.ifmo.se.kastricyn.lab7.lib.connection.ServerRequestType;
import ru.ifmo.se.kastricyn.lab7.lib.utility.ByteInputStream;
import ru.ifmo.se.kastricyn.lab7.lib.utility.ByteOutputStream;
import ru.ifmo.se.kastricyn.lab7.server.commandManager.NetCommandManager;
import ru.ifmo.se.kastricyn.lab7.server.commands.ServerAbstractCommand;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Arrays;
import java.util.stream.Collectors;

public class Client {
    final static Logger log = LogManager.getLogger();

    private ByteBuffer bf = ByteBuffer.wrap(new byte[10240]);
    private ObjectInputStream ois;
    private ByteOutputStream bos = new ByteOutputStream();
    private ObjectOutputStream oos;
    private ByteInputStream bis = new ByteInputStream();
    private SocketChannel sh;

    public Client(@NotNull ServerSocketChannel ssc, Selector selector) throws IOException, InterruptedException, ClassNotFoundException {
        sh = ssc.accept();
        if (sh == null)
            return;
        sh.configureBlocking(false);
        sh.register(selector, SelectionKey.OP_READ, this);
        sh.finishConnect();
        System.out.println(sh.getRemoteAddress());
        ServerRequest request = read();
        if (ServerRequestType.INITIALIZATION.equals(request.getType()))
            write(new ServerAnswer(ServerAnswerType.OK));
        log.info("Подключено " + sh.getRemoteAddress());
    }

    public void reply(@NotNull NetCommandManager cm) throws IOException, InterruptedException, ClassNotFoundException {
        try {
            write(processing(read(), cm));
        } catch (IOException e) {
            oos.close();
            bos.close();
            ois.close();
            bis.close();
            sh.close();
            log.info("соединение с " + sh.getRemoteAddress() + " закрыто");
        }
    }

    protected @NotNull ServerAnswer processing(@NotNull ServerRequest serverRequest, @NotNull NetCommandManager cm) {
        //получим команду по имени
        ServerAbstractCommand command = (ServerAbstractCommand) cm.getCommand(serverRequest.getInput().split("\\s", 2)[0]);
        //если команды нет, отправим ответ
        if (command == null)
            return new ServerAnswer(ServerAnswerType.NOT_FOUND_COMMAND);
        //устанавливаем аргументы
        assert serverRequest.getObjArgs() != null;
        ServerCommandArgument ca = new ServerCommandArgument(serverRequest.getObjArgs())
                .setCommandManager(cm).setTicketCollection(cm.getTicketCollection());
        command.setArguments(ca);
        // проверяем аргументы и запускаем команду
        if (command.objectsArgsIsValidate()) {
            String[] input = serverRequest.getInput().split("\\s");
            if (input.length > 1)
                command.execute(Arrays.copyOfRange(input, 1, input.length));
            else command.execute();
            return new ServerAnswer(ServerAnswerType.OK).setAnswer(command.getAnswer());
        } else
            return new ServerAnswer(ServerAnswerType.NEED_ARGS).setInput(serverRequest.getInput())
                    .setArgTypes(command.getArgumentTypes().stream()
                            .filter(x -> !(x.equals(TicketCollection.class)||x.equals(CommandManager.class)))
                            .collect(Collectors.toSet()));

    }

    //это сервер
    @SuppressWarnings("deprecation")
    protected void write(ServerAnswer sa) throws IOException {
        if (oos == null)
            oos = new ObjectOutputStream(bos);
        oos.writeObject(sa);
        oos.flush();
        sh.write(ByteBuffer.wrap(bos.toByteArray()));
        bos.reset();
        log.debug("Отправлено " + sh.getRemoteAddress() + ":");
        log.debug(sa);
    }

    protected ServerRequest read() throws IOException, InterruptedException, ClassNotFoundException {
        bf.clear();
        do {
            System.out.println(bf.position());
            Thread.sleep(50);
        } while ((sh.read(bf) > 0));
        bis.setBuf(bf.array());
        if (ois == null)
            ois = new ObjectInputStream(bis);
        ServerRequest request = (ServerRequest) ois.readObject();
        log.debug("Принято " + sh.getRemoteAddress() + ":");
        log.debug(request);
        return request;
    }


}
