package ru.ifmo.se.kastricyn.lab6.server;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.ifmo.se.kastricyn.lab6.lib.connection.ServerAnswer;
import ru.ifmo.se.kastricyn.lab6.lib.connection.ServerAnswerType;
import ru.ifmo.se.kastricyn.lab6.lib.connection.ServerRequest;
import ru.ifmo.se.kastricyn.lab6.lib.utility.Parser;
import ru.ifmo.se.kastricyn.lab6.server.commandManager.NetCommandManager;

import javax.xml.bind.JAXBException;
import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Arrays;

public class Client {
    final static Logger log = LogManager.getLogger();

    private SocketChannel sh;
    private Selector selector;

    public Client(@NotNull ServerSocketChannel ssc, Selector selector) throws IOException {
        sh = ssc.accept();
        if (sh == null)
            return;
        sh.configureBlocking(false);
        this.selector = selector;
        sh.register(selector, SelectionKey.OP_READ, this);
        log.info("Подключено " + sh.getRemoteAddress());
    }

    public void reply(@NotNull ByteBuffer bf, @NotNull NetCommandManager cm) {
        try {
            write(processing(read(bf), cm));
        } catch (@NotNull IOException | JAXBException e) {
            try {
                log.info("соединение с " + sh.getRemoteAddress() + " закрыто");
            } catch (IOException ioException) {
                log.error(ioException.getMessage());
            }
            try {
                sh.close();
            } catch (IOException ioException) {
                log.error(ioException.getMessage());
            }
            return;
        }
        if (sh.isConnected()) {
            try {
                sh.register(selector, SelectionKey.OP_READ, this);
            } catch (ClosedChannelException e) {
                log.warn(e.getMessage());
            }
        }
    }

    protected @NotNull ServerAnswer processing(@NotNull ServerRequest serverRequest, @NotNull NetCommandManager cm) {
        //получим команду по имени
        ServerAbstractCommand command = (ServerAbstractCommand) cm.getCommand(serverRequest.getInput().split("\\s", 2)[0]);
        //если команды нет, отправим ответ
        if (command == null)
            return new ServerAnswer(ServerAnswerType.NOT_FOUND_COMMAND);
        //устанавливаем аргументы
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
                    .setArgTypes(command.getArgumentTypes());
    }

    protected void write(ServerAnswer sa) throws IOException {
        StringWriter sw = new StringWriter();
        Parser.write(sw, ServerAnswer.class, sa);
//        sh.write(ByteBuffer.wrap(sw.toString().getBytes(StandardCharsets.UTF_8)));
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(byteArrayOutputStream);
        oos.writeObject(sa);
        sh.write(ByteBuffer.wrap(byteArrayOutputStream.toByteArray()));

        log.info("Отправлено " + sh.getRemoteAddress() + ":");
        log.debug(sa);
    }

    protected @Nullable ServerRequest read(@NotNull ByteBuffer bf) throws IOException, JAXBException {
        sh.read(bf);
        bf.flip();
//        ServerRequest request = Parser.get(new StringReader(new String(bf.array(), bf.position(), bf.remaining(), "UTF-8")), ServerRequest.class);
        ServerRequest request = null;
        try {
            request = (ServerRequest) new ObjectInputStream(new ByteArrayInputStream(bf.array())).readObject();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        bf.clear();
        log.info("Принято " + sh.getRemoteAddress() + ":");
        log.debug(request);
        return request;
    }


}
