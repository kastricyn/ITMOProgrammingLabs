package ru.ifmo.se.kastricyn.lab7.client;

import org.jetbrains.annotations.NotNull;
import ru.ifmo.se.kastricyn.lab7.lib.utility.Console;

import java.io.IOException;
import java.net.InetAddress;
import java.nio.file.Paths;
import java.util.NoSuchElementException;

/**
 * Main-class
 */
public class Main {
    //todo: properties
    public static InetAddress INET_ADDRESS = InetAddress.getLoopbackAddress();

    /**
     * @param args путь до файла с конфигами, если не работает, то будут подставлены значения по умоланию
     */
    public static void main(String @NotNull [] args) throws IOException {
        Properties property = (Properties) Properties.getProperties().load(Paths.get("config"));
        Console console = new Console();

        try (Connection connect = new Connection(INET_ADDRESS, property.getPort(), property.getJavaProperties())) {
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

