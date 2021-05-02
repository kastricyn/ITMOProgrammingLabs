package ru.ifmo.se.kastricyn.lab6.server;

import ru.ifmo.se.kastricyn.lab6.lib.ServerAnswer;
import ru.ifmo.se.kastricyn.lab6.lib.ServerRequest;
import ru.ifmo.se.kastricyn.lab6.lib.utility.Parser;

import javax.xml.bind.JAXBException;
import java.io.*;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.nio.charset.StandardCharsets;
import java.util.Iterator;

public class Connect {
    //todo: read params from properties
    static final int PORT = 8189;


    public static void getConnect() throws IOException, JAXBException {
        ServerSocketChannel ssc = ServerSocketChannel.open()
                .bind(new InetSocketAddress(PORT));
        ssc.configureBlocking(false);

        Selector selector = Selector.open();
        ssc.register(selector, SelectionKey.OP_ACCEPT);

        while (true) {
            if (selector.select() == 0)
                continue;

            Iterator<SelectionKey> iter = selector.selectedKeys().iterator();
            while (iter.hasNext()) {
                ByteBuffer bf = ByteBuffer.allocate(1024 * 1024);
                SelectionKey key = iter.next();
                iter.remove();
                if (key.isValid()) {
                    if (key.isAcceptable()) {
                        SocketChannel sh = ((ServerSocketChannel) key.channel()).accept();
                        sh.configureBlocking(false);
                        sh.register(key.selector(), SelectionKey.OP_READ);
                    }
                    if (key.isReadable()) {
                        SocketChannel sh = (SocketChannel) key.channel();
                        sh.configureBlocking(false);
                        sh.read(bf);
                        bf.flip();
                        ServerRequest address = Parser.get(new StringReader(new String(bf.array(), bf.position(), bf.remaining(), "UTF-8")), ServerRequest.class);
                        bf.clear();
                        System.out.println(address);
                        sh.register(key.selector(), SelectionKey.OP_WRITE);
                    }
                    if (key.isWritable()) {
                        try (SocketChannel sh = (SocketChannel) key.channel()) {
                            sh.configureBlocking(false);
                            StringWriter sw = new StringWriter();
                            Parser.write(sw, ServerAnswer.class,
                                    new ServerAnswer("ответ сервера на запрос 1"));
                            sh.write(ByteBuffer.wrap(sw.toString().getBytes(StandardCharsets.UTF_8)));
                        }
                        ssc.register(key.selector(), SelectionKey.OP_ACCEPT);
                    }

                }

            }

        }
    }

}
