package ru.ifmo.se.kastricyn.lab6.server;

import ru.ifmo.se.kastricyn.lab6.lib.data.Address;
import ru.ifmo.se.kastricyn.lab6.server.commands.Add;

import java.io.*;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.Channels;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

public class Connect implements Closeable {
    //todo: read params from properties
    static final int PORT = 8189;
    private ObjectInputStream objectInputStream;
    private ObjectOutputStream objectOutputStream;


    public static void getConnect() throws IOException, ClassNotFoundException {
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open()
                .bind(new InetSocketAddress(PORT));
        if(serverSocketChannel==null)
            return;
        while(true) {
            SocketChannel channel = serverSocketChannel.accept();
            serverSocketChannel.close();
            ByteBuffer buffer = ByteBuffer.allocate(1024 * 1024);
            channel.read(buffer);
            buffer.flip();

            ByteArrayInputStream bais = new ByteArrayInputStream(buffer.array(), buffer.position(), buffer.remaining());
            ObjectInputStream objectInputStream = new ObjectInputStream(bais);
            System.out.println("стрессс");
            System.out.println(((Address)objectInputStream.readObject()).getStreet());
            objectInputStream.close();
            bais.close();

        }
    }

    public ObjectInputStream getObjectInputStream() {
        return objectInputStream;
    }

    public ObjectOutputStream getObjectOutputStream() {
        return objectOutputStream;
    }

    @Override
    public void close() throws IOException {

    }
}
