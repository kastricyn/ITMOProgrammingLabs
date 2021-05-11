package ru.ifmo.se.kastricyn.lab6.server;

import ru.ifmo.se.kastricyn.lab6.lib.Command;
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
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

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
        Command command = cm.getCommand(serverRequest.getInput().split("\\s", 2)[0]);
        if (command == null)
            return new ServerAnswer(ServerAnswerType.NOT_FOUND_COMMAND);
        Iterator<Object> iter = serverRequest.getObjectsArgs().iterator();
        AbstractList<Object> args = new ArrayList<>();
        for (Class type :
                command.getArgumentTypes()) {
            if (type.equals(TicketCollection.class))
                args.add(cm.getTicketCollection());
            else if (type.isInstance(cm))
                args.add(cm);
            else if (iter.hasNext())
                args.add(iter.next());
            else
                return new ServerAnswer(serverRequest.getInput(), ServerAnswerType.MISTAKE_ARGS)
                        .setObjectsArgsTypes(command.getArgumentTypes());
        }
        command.setArguments(args);
        if (command.objectsArgsIsValidate()) {
            String[] stringArgs = serverRequest.getInput().split("\\s");
            if (stringArgs.length > 1)
                command.execute(Arrays.copyOfRange(stringArgs, 1, stringArgs.length));
            else command.execute();
            return new ServerAnswer(command.getAnswer(), ServerAnswerType.OK);
        } else
            return new ServerAnswer(serverRequest.getInput(), ServerAnswerType.MISTAKE_ARGS)
                    .setObjectsArgsTypes(command.getArgumentTypes());

    }

    protected void write(ServerAnswer sa) throws IOException {
        StringWriter sw = new StringWriter();
        Parser.write(sw, ServerAnswer.class, sa);
        sh.write(ByteBuffer.wrap(sw.toString().getBytes(StandardCharsets.UTF_8)));
    }

    protected ServerRequest read(ByteBuffer bf) throws IOException, JAXBException {
        sh.read(bf);
        bf.flip();
        ServerRequest request = Parser.get(new StringReader(new String(bf.array(), bf.position(), bf.remaining(), "UTF-8")), ServerRequest.class);
        bf.clear();
        return request;
    }


}
