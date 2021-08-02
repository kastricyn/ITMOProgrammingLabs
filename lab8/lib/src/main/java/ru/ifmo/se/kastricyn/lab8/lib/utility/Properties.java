package ru.ifmo.se.kastricyn.lab8.lib.utility;

import org.jetbrains.annotations.NotNull;

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
public class Properties {
    protected final java.util.Properties prop = new java.util.Properties();

    protected Properties() {
    }

    public java.util.Properties getJavaProperties() {
        return prop;
    }

    public @NotNull Properties load(@NotNull Path p) throws IOException {
        prop.load(new FileReader(p.toFile()));
        return this;
    }

    public int getPort() {
        return Integer.parseInt(prop.getProperty("port", "27222"));
    }

}
