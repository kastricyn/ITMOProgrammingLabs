package ru.ifmo.se.kastricyn.lab6.server;

import ru.ifmo.se.kastricyn.lab6.lib.connection.ServerAnswer;
import ru.ifmo.se.kastricyn.lab6.lib.connection.ServerAnswerType;
import ru.ifmo.se.kastricyn.lab6.lib.connection.ServerRequest;
import ru.ifmo.se.kastricyn.lab6.lib.utility.Parser;
import ru.ifmo.se.kastricyn.lab6.server.commandManager.NetCommandManager;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

public class Client {
    private SocketChannel sh;
    private Selector selector;

    public Client(ServerSocketChannel ssc, Selector selector) throws IOException {
        sh = ssc.accept();
        if (sh == null)
            return;
        sh.configureBlocking(false);
        this.selector = selector;
        sh.register(selector, SelectionKey.OP_READ, this);
    }

    public void reply(ByteBuffer bf, NetCommandManager cm) {
        try {
            write(processing(read(bf), cm));
        } catch (IOException | JAXBException e) {
            System.out.println("Конец соединения");
            try {
                sh.close();
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
            return;
        }
        if (sh.isConnected()) {
            try {
                sh.register(selector, SelectionKey.OP_READ, this);
            } catch (ClosedChannelException e) {
                e.printStackTrace();
            }
        }
    }

    protected ServerAnswer processing(ServerRequest serverRequest, NetCommandManager cm) {
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
        sh.write(ByteBuffer.wrap(sw.toString().getBytes(StandardCharsets.UTF_8)));
//        System.err.println("Отправлено: "+ sa);
    }

    protected ServerRequest read(ByteBuffer bf) throws IOException, JAXBException {
        sh.read(bf);
        bf.flip();
        ServerRequest request = Parser.get(new StringReader(new String(bf.array(), bf.position(), bf.remaining(), "UTF-8")), ServerRequest.class);
        bf.clear();
//        System.err.println("Принято: " + request);
        return request;
    }


}
