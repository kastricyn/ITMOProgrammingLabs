package ru.ifmo.se.kastricyn.lab6.client;


import ru.ifmo.se.kastricyn.lab6.lib.data.Address;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.nio.ByteBuffer;

/**
 * Main-class
 */
public class Main {
    public static void main(String[] args) throws IOException {
        System.out.println("Привет, я клиент!\n и получил:");
        try (Socket socket = new Socket(InetAddress.getLoopbackAddress(), 8189)) {
            byte[] b = new byte[1024*1024];
            ByteArrayOutputStream bais = new ByteArrayOutputStream(1024*1024);
            new ObjectOutputStream(bais).writeObject(new Address("Just address"));


        }
    }

}

