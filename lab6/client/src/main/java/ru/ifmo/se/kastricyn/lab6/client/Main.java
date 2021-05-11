package ru.ifmo.se.kastricyn.lab6.client;

import ru.ifmo.se.kastricyn.lab6.lib.utility.Console;

import java.io.IOException;
import java.net.InetAddress;

/**
 * Main-class
 */
public class Main {
    //todo: properties
    public static final InetAddress INET_ADDRESS = InetAddress.getLoopbackAddress();
    public static final int PORT = 8189;

    public static void main(String[] args) {
        Console console = new Console();
        try (Connection connect = new Connection(InetAddress.getLocalHost(), 8189)) {
            console.println("Соединение установлено");
            ClientCommandManager manager = new ClientCommandManager(connect, console);
            manager.run();

        } catch (IOException e) {
            new Console().printlnErr("Соединение не установлено, попробуйте проверьте параметры и попробуйте позже.");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}

