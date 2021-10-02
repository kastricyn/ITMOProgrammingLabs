package ru.ifmo.se.kastricyn.lab8.client.console;

import org.jetbrains.annotations.NotNull;

public final class Properties extends ru.ifmo.se.kastricyn.lab8.lib.utility.Properties {
    public static final Properties properties = new Properties();

    private Properties() {
        super();
    }

    @NotNull
    public static Properties getProperties() {
        return properties;
    }

}
