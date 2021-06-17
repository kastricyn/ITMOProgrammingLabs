package ru.ifmo.se.kastricyn.lab7.client;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.ifmo.se.kastricyn.lab7.lib.connection.ServerAnswer;
import ru.ifmo.se.kastricyn.lab7.lib.connection.ServerAnswerType;
import ru.ifmo.se.kastricyn.lab7.lib.connection.ServerRequest;
import ru.ifmo.se.kastricyn.lab7.lib.connection.ServerRequestType;

import java.io.Closeable;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.util.Properties;

//todo properties
public class Connection implements Closeable {
    public static int MAX_ATTEMPT = 5;
    public static int MAX_TIMEOUT = 10000;
    public static int INTERVAL = 5000;

    private Socket socket;
    private ObjectOutputStream oos;
    private ObjectInputStream ois;

    /**
     * Устанавливает соединение с сервером по TCP
     *
     * @param sa с кем установить подключение
     * @throws IOException          если подкючение не установлено
     * @throws InterruptedException
     */
    public Connection(SocketAddress sa, @NotNull Properties properties) throws IOException, InterruptedException {
        try {
            MAX_ATTEMPT = Integer.parseInt(properties.getProperty("max_attempt", "5"));
            MAX_TIMEOUT = Integer.parseInt(properties.getProperty("max_timeout", "3000"));
            INTERVAL = Integer.parseInt(properties.getProperty("interval", "5000"));
        } catch (NumberFormatException e) {
            System.err.println("Не все параметры удалось верно интерпретировать, некоторые будут использоваться " +
                    "поумолчанию");
        }
        for (int i = 0; i < MAX_ATTEMPT; i++) {
            try {
                socket = new Socket();
                socket.connect(sa, MAX_TIMEOUT);
                oos = new ObjectOutputStream(socket.getOutputStream());
                oos.writeObject(new ServerRequest(ServerRequestType.INITIALIZATION));
                ois = new ObjectInputStream(socket.getInputStream());
                ServerAnswer t = (ServerAnswer) ois.readObject();
                if (ServerAnswerType.OK.equals(t.getSat()))
                    System.out.println("Connected");
                else throw new IOException("Not connected");
            } catch (IOException | ClassNotFoundException e) {
                System.out.println("подключение не установлено, слеудущаяя попытка через " + INTERVAL / 1000. + " с" +
                        ".");
            }
            if (socket.isConnected())
                break;
            Thread.sleep(INTERVAL);
        }
        if (!socket.isConnected())
            throw new IOException();

    }

    public Connection(InetAddress ia, int port, @NotNull Properties properties) throws IOException, InterruptedException {
        this(new InetSocketAddress(ia, port), properties);
    }

    public @Nullable ServerAnswer getAnswer(ServerRequest request) throws IOException, ClassNotFoundException {
        sendRequest(request);
        return getAnswer();
    }
    //это клиент
    public void sendRequest(ServerRequest request) throws IOException {
            oos.writeObject(request);
            oos.flush();
//        System.err.println("Отправлено: " + request);
    }


    public @Nullable ServerAnswer getAnswer() throws IOException, ClassNotFoundException {
        return (ServerAnswer) ois.readObject();
    }


    @Override
    public void close() throws IOException {
        socket.close();
    }
}
