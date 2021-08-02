package ru.ifmo.se.kastricyn.lab8.server.commandManager;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jetbrains.annotations.NotNull;
import ru.ifmo.se.kastricyn.lab8.lib.CommandManager;
import ru.ifmo.se.kastricyn.lab8.lib.connection.ServerAnswer;
import ru.ifmo.se.kastricyn.lab8.lib.connection.ServerRequest;
import ru.ifmo.se.kastricyn.lab8.server.Client;
import ru.ifmo.se.kastricyn.lab8.server.Properties;
import ru.ifmo.se.kastricyn.lab8.server.TicketCollection;
import ru.ifmo.se.kastricyn.lab8.server.commands.*;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.util.Iterator;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;

public class NetCommandManager extends CommandManager {
    static final Logger log = LogManager.getLogger();

    static int PORT = Properties.getProperties().getPort();
    private final TicketCollection ticketCollection;
    private final ServerSocketChannel ssc;
    private final Selector selector;

    public NetCommandManager(TicketCollection ticketCollection, int port) throws IOException {
        this.ticketCollection = ticketCollection;
        ssc = ServerSocketChannel.open()
                .bind(new InetSocketAddress(port));
        ssc.configureBlocking(false);

        selector = Selector.open();
        ssc.register(selector, SelectionKey.OP_ACCEPT);
        log.info(toString());
    }

    /**
     * Возвращает менеджер комманд поумолчанию
     *
     * @param ticketCollection коллекция с которой будут работать команды
     */
    public static @NotNull NetCommandManager getStandards(TicketCollection ticketCollection) throws IOException {
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
        ncm.addIfAbsent(new LogIn());
        ncm.addIfAbsent(new Register());
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
    public void executeCommand(String commandName, String... args) {
        //не используется, здесь вместо этого используется Client.processing(....)
    }

    /**
     * Обрабатывает команды, пока они поступают от пользователя.
     * Надо переопределить для конкртной реализации
     */
    @Override
    public void run() {
        System.out.println("Данные для подключения: " + this);
        ForkJoinPool processingExecutorService = new ForkJoinPool();
        ExecutorService writingExecutorService = Executors.newCachedThreadPool();
        ExecutorService readingExecutorService = Executors.newFixedThreadPool(16);
        while (isWorkable()) {
            if (Thread.interrupted()) {
                readingExecutorService.shutdown();
                processingExecutorService.shutdown();
                writingExecutorService.shutdown();
                return;
            }
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
                            NetCommandManager cm = this;
                            if (key.attachment() instanceof Client) {
                                Client client = (Client) key.attachment();
                                key.channel().register(selector, SelectionKey.OP_WRITE);
                                readingExecutorService.submit(() -> {
                                    try {
                                        ServerRequest sr = client.read();
                                        processingExecutorService.invoke(new RecursiveAction() {
                                            @Override
                                            protected void compute() {
//                                        synchronized (sa) {
                                                final ServerAnswer sa = client.processing(sr, cm);
                                                writingExecutorService.submit(() -> {
                                                    client.write(sa);
                                                    key.channel().register(selector, SelectionKey.OP_READ);
                                                    key.attach(client);
                                                    return null;
                                                });
//                                        }
//
                                            }
                                        });
                                    } catch (IOException e) {
                                        key.cancel();
                                        try {
                                            client.close();
                                        } catch (IOException ioException) {
                                            ioException.printStackTrace();
                                        }
                                    } catch (InterruptedException | ClassNotFoundException e) {
                                        e.printStackTrace();
                                    }

                                });

                            }
                        }
                    }
                }
            } catch (IOException | RuntimeException | InterruptedException e) {
                log.warn(e.getStackTrace());
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }

    }

    @Override
    public @NotNull String toString() {
        return "порт подключения:" + PORT;
    }
}
