package ru.ifmo.se.kastricyn.lab7.client;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.ifmo.se.kastricyn.lab7.lib.connection.ServerAnswer;
import ru.ifmo.se.kastricyn.lab7.lib.connection.ServerRequest;
import ru.ifmo.se.kastricyn.lab7.lib.utility.Parser;

import javax.xml.bind.JAXBException;
import java.io.*;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.util.Properties;

//todo properties
public class Connection implements Closeable {
    public static int MAX_ATTEMPT = 5;
    public static int MAX_TIMEOUT = 3000;
    public static int INTERVAL = 5000;

    private Socket socket;
    private ObjectInputStream is;
    private ObjectOutputStream os;

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
            } catch (IOException e) {
                System.out.println("подключение не установлено, слеудущаяя попытка через " + INTERVAL / 1000. + " с" +
                        ".");
            }
            if (socket.isConnected())
                break;
            Thread.sleep(INTERVAL);
        }
        if (!socket.isConnected())
            throw new IOException();
        is = new ObjectInputStream(socket.getInputStream());
        os = new ObjectOutputStream(socket.getOutputStream());
    }

    public Connection(InetAddress ia, int port, @NotNull Properties properties) throws IOException, InterruptedException {
        this(new InetSocketAddress(ia, port), properties);
    }

    public @Nullable ServerAnswer getAnswer(ServerRequest request) throws IOException, JAXBException {
        sendRequest(request);
        return getAnswer();
    }

    public void sendRequest(ServerRequest request) throws IOException {
        StringWriter sw = new StringWriter();
        Parser.write(sw, ServerRequest.class, request);
//        os.write(sw.toString().getBytes(StandardCharsets.UTF_8));

//        ByteArrayInputStream bais = new ByteArrayInputStream();
//        ObjectInputStream ois = new ObjectInputStream(bais);
        new ObjectOutputStream(System.out).writeObject(request);
        os.writeObject(request);

//        System.err.println("Отправлено: " + request);
    }


    public @Nullable ServerAnswer getAnswer() throws IOException, JAXBException {
        byte[] b = new byte[1024 * 1024];
//        int len = is.read(b);
//        ServerAnswer sa = Parser.get(new StringReader(new String(b, 0, len, "UTF-8")), ServerAnswer.class);
        ServerAnswer sa = null;
        try {
//            new ObjectInputStream(is).readObject();
            sa = (ServerAnswer) is.readObject();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
//        System.err.println("Получено:" + sa);
        return sa;
    }


    @Override
    public void close() throws IOException {
        is.close();
        os.close();
        socket.close();
    }
}
