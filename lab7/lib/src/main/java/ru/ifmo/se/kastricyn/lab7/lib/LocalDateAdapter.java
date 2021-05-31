package ru.ifmo.se.kastricyn.lab7.lib;

import org.jetbrains.annotations.NotNull;

import javax.xml.bind.annotation.adapters.XmlAdapter;
import java.time.LocalDate;

/**
 * Показывает как преобразовывать объект класса LocalDate в строку.
 * Используется JAXB-ом, при сохранении и восстановлении коллекции в/из файл(-а).
 */
public class LocalDateAdapter extends XmlAdapter<String, LocalDate> {
    public LocalDate unmarshal(@NotNull String v) {
        return LocalDate.parse(v);
    }

    public @NotNull String marshal(@NotNull LocalDate v) {
        return v.toString();
    }
}