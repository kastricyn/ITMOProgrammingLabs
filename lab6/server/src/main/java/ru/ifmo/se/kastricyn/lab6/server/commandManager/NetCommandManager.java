package ru.ifmo.se.kastricyn.lab6.server.commandManager;

import ru.ifmo.se.kastricyn.lab6.lib.AbstractCommandManager;
import ru.ifmo.se.kastricyn.lab6.lib.utility.Console;
import ru.ifmo.se.kastricyn.lab6.server.Client;
import ru.ifmo.se.kastricyn.lab6.server.TicketCollection;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.util.Iterator;

public class NetCommandManager extends AbstractCommandManager {
    //todo: read params from properties
    static final int PORT = 8189;

    private final TicketCollection ticketCollection;
    private final ServerSocketChannel ssc;
    private final ByteBuffer bf;
    private final Selector selector;

    public NetCommandManager(TicketCollection ticketCollection, int port) throws IOException {
        this.ticketCollection = ticketCollection;
        ssc = ServerSocketChannel.open()
                .bind(new InetSocketAddress(port));
        ssc.configureBlocking(false);

        selector = Selector.open();
        ssc.register(selector, SelectionKey.OP_ACCEPT);
        bf = ByteBuffer.allocate(1024 * 1024);
    }

    /**
     * Возвращает менеджер комманд поумолчанию
     *
     * @param ticketCollection коллекция с которой будут работать команды
     * @param console          объект типа {@link Console} с помощью которого происходит взаимодействие с пользователем
     */
    public static NetCommandManager getStandards(TicketCollection ticketCollection) {
        //todo
        return null;
    }

    /**
     * Исполняет команду, имя которой передано в первом аргументе, если она доступна в менеджере команд
     *
     * @param commandName имя команды
     * @param args        аргументы команды в строковом представлении
     */
    @Override
    public void executeCommand(String commandName, String... args) {

    }

    /**
     * Обрабатывает команды, пока они поступают от пользователя.
     * Надо переопределить для конкртной реализации
     */
    @Override
    public void run() {
        while (true) {
            try {
                selector.select();
                Iterator<SelectionKey> iter = selector.selectedKeys().iterator();
                while (iter.hasNext()) {
                    SelectionKey key = iter.next();
                    if (key.isValid()) {
                        iter.remove();
                        if (key.isAcceptable())
                            new Client(ssc, selector);
                        if (key.isReadable()) {
                            if (key.attachment() instanceof Client)
                                ((Client) key.attachment()).reply(bf, this);
                            bf.clear();
                        }
                    }

                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

}
