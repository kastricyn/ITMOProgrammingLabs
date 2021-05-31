package ru.ifmo.se.kastricyn.lab7.lib.utility;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;

/**
 * Класс описывает синглтон объект, содержащий все конфигурируемы параметры приложения.
 * Класс наследник должен быть final, с единственным private конструктором, содержащий
 * <code>    public static final Properties properties = new Properties();
 * public static getProperties(){return properties;}
 * </code>
 * и необходимыми методами
 */
public abstract class Properties {
    protected final java.util.Properties prop = new java.util.Properties();

    protected Properties() {
    }

    public Properties load(Path p) throws FileNotFoundException, IOException {
        prop.load(new FileReader(p.toFile()));
        return this;
    }

    public int getPort() {
        return Integer.parseInt(prop.getProperty("port", "27222"));
    }

}
