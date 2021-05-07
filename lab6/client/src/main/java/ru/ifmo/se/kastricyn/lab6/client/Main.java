package ru.ifmo.se.kastricyn.lab6.client;

import ru.ifmo.se.kastricyn.lab6.lib.connection.ServerRequest;
import ru.ifmo.se.kastricyn.lab6.lib.utility.Console;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.net.InetAddress;

/**
 * Main-class
 */
public class Main {
    public static void main(String[] args) {
        System.out.println("Привет, я клиент!\n и получил:");
         {
            try (Connection connect = new Connection(InetAddress.getLocalHost(), 8189)) {
                for (int i = 0; i < 10; i++) {
                    System.out.println(connect.sendRequest(new ServerRequest("just request" + i)));
                    Thread.sleep(1000);
                }
            } catch (IOException e) {
                new Console().printlnErr("Соединение прервалось");
            } catch (InterruptedException | JAXBException e) {
                e.printStackTrace();
            }
        }
    }

}

