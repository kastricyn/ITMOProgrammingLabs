package ru.ifmo.se.kastricyn.lab6.client;

import org.jetbrains.annotations.NotNull;
import ru.ifmo.se.kastricyn.lab6.lib.utility.Console;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.util.NoSuchElementException;
import java.util.Properties;

/**
 * Main-class
 */
public class Main {
    //todo: properties
    public static InetAddress INET_ADDRESS = InetAddress.getLoopbackAddress();
    public static int PORT;
    static @NotNull Properties property = new Properties();

    /**
     * @param args путь до файла с конфигами, если не работает, то будут подставлены значения по умоланию
     */
    public static void main(String @NotNull [] args) {
        Console console = new Console();
        if (args.length > 0)
            try (FileInputStream fis = new FileInputStream(args[0])) {
                property.load(fis);
            } catch (IOException e) {
                console.printlnErr("Файл с конфигурациями не доступен, будут использованы значения поумолчанию.");
            }


        PORT = Integer.parseInt(property.getProperty("port", "8189"));

        try (Connection connect = new Connection(INET_ADDRESS, PORT, property)) {
            console.println("Соединение установлено");
            ClientCommandManager manager = ClientCommandManager.getStandards(connect, console);
            manager.run();

        } catch (NoSuchElementException e) {
            System.out.println("Программа завершена пользователем");
        } catch (@NotNull IOException | IllegalArgumentException e) {
            new Console().printlnErr("Соединение не установлено, проверьте параметры и попробуйте позже.");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}

