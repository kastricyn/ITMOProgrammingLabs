package ru.ifmo.se.kastricyn.lab6.client;

import ru.ifmo.se.kastricyn.lab6.lib.connection.ServerAnswer;
import ru.ifmo.se.kastricyn.lab6.lib.connection.ServerRequest;
import ru.ifmo.se.kastricyn.lab6.lib.utility.Parser;

import javax.xml.bind.JAXBException;
import java.io.*;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.nio.charset.StandardCharsets;
import java.util.Properties;

//todo properties
public class Connection implements Closeable {
    public static int MAX_ATTEMPT = 5;
    public static int MAX_TIMEOUT = 3000;
    public static int INTERVAL = 5000;

    private Socket socket;
    private InputStream is;
    private OutputStream os;

    /**
     * Устанавливает соединение с сервером по TCP
     *
     * @param sa с кем установить подключение
     * @throws IOException          если подкючение не установлено
     * @throws InterruptedException
     */
    public Connection(SocketAddress sa, Properties properties) throws IOException, InterruptedException {
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
        is = socket.getInputStream();
        os = socket.getOutputStream();
    }

    public Connection(InetAddress ia, int port, Properties properties) throws IOException, InterruptedException {
        this(new InetSocketAddress(ia, port), properties);
    }

    public ServerAnswer getAnswer(ServerRequest request) throws IOException, JAXBException {
        sendRequest(request);
        return getAnswer();
    }

    public void sendRequest(ServerRequest request) throws IOException {
        StringWriter sw = new StringWriter();
        Parser.write(sw, ServerRequest.class, request);
        os.write(sw.toString().getBytes(StandardCharsets.UTF_8));
//        System.err.println("Отправлено: " + request);
    }


    public ServerAnswer getAnswer() throws IOException, JAXBException {
        byte[] b = new byte[1024 * 1024];
        int len = is.read(b);
        ServerAnswer sa = Parser.get(new StringReader(new String(b, 0, len, "UTF-8")), ServerAnswer.class);
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
