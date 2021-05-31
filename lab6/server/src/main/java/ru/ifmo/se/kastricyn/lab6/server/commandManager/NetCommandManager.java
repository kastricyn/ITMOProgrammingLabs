package ru.ifmo.se.kastricyn.lab6.server.commandManager;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jetbrains.annotations.NotNull;
import ru.ifmo.se.kastricyn.lab6.lib.CommandManager;
import ru.ifmo.se.kastricyn.lab6.server.Client;
import ru.ifmo.se.kastricyn.lab6.server.TicketCollection;
import ru.ifmo.se.kastricyn.lab6.server.commands.*;

import java.io.FileReader;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.util.Iterator;
import java.util.Properties;

public class NetCommandManager extends CommandManager {
    static final Logger log = LogManager.getLogger();

    //todo: read params from properties
    static int PORT = 8189;
    private final TicketCollection ticketCollection;
    private final ServerSocketChannel ssc;
    private final @NotNull ByteBuffer bf;
    private final Selector selector;

    public NetCommandManager(TicketCollection ticketCollection, int port) throws IOException {
        this.ticketCollection = ticketCollection;
        ssc = ServerSocketChannel.open()
                .bind(new InetSocketAddress(port));
        ssc.configureBlocking(false);

        selector = Selector.open();
        ssc.register(selector, SelectionKey.OP_ACCEPT);
        bf = ByteBuffer.allocate(1024 * 1024);
        log.info(toString());
    }

    /**
     * Возвращает менеджер комманд поумолчанию
     *
     * @param ticketCollection коллекция с которой будут работать команды
     */
    public static @NotNull NetCommandManager getStandards(TicketCollection ticketCollection) throws IOException {
        try {
            Properties properties = new Properties();
            properties.load(new FileReader("config"));
            PORT = Integer.parseInt(properties.getProperty("port", "8189"));
        } catch (Exception e) {
            log.trace("config файл не найден");
        }
        NetCommandManager ncm = new NetCommandManager(ticketCollection, PORT);
        ncm.addIfAbsent(new Add());
        ncm.addIfAbsent(new AddIfMax());
        ncm.addIfAbsent(new Clear());
        ncm.addIfAbsent(new FilterByVenue());
        ncm.addIfAbsent(new Head());
        ncm.addIfAbsent(new Help());
        ncm.addIfAbsent(new Info());
        ncm.addIfAbsent(new PrintAscending());
        ncm.addIfAbsent(new PrintFieldDescendingVenue());
        ncm.addIfAbsent(new RemoveById());
        ncm.addIfAbsent(new RemoveLower());
        ncm.addIfAbsent(new Show());
        ncm.addIfAbsent(new Update());
        return ncm;
    }

    public TicketCollection getTicketCollection() {
        return ticketCollection;
    }

    /**
     * Исполняет команду, имя которой передано в первом аргументе, если она доступна в менеджере команд
     *
     * @param commandName имя команды
     * @param args        аргументы команды в строковом представлении
     */
    @Override
    public void executeCommand(String commandName, String ... args) {
        //не используется, здесь вместо этого используется Client.processing(....)
    }

    /**
     * Обрабатывает команды, пока они поступают от пользователя.
     * Надо переопределить для конкртной реализации
     */
    @Override
    public void run() {
        while (isWorkable()) {
            if (Thread.interrupted())
                return;
            try {
                if (selector.select() == 0) {
                    continue;
                }
                Iterator<SelectionKey> iter = selector.selectedKeys().iterator();
                while (iter.hasNext()) {
                    SelectionKey key = iter.next();
                    if (key.isValid()) {
                        iter.remove();
                        if (key.isAcceptable()) {
                            new Client(ssc, selector);
                        }
                        if (key.isReadable()) {
                            if (key.attachment() instanceof Client) {
                                ((Client) key.attachment()).reply(bf, this);
                            }
                            bf.clear();
                        }
                    }

                }
            } catch (IOException e) {
                log.error(e.getMessage());
            }
        }

    }

    @Override
    public @NotNull String toString() {
        return "порт подключения:" + PORT;
    }
}
