package ru.ifmo.se.kastricyn.lab6.lib;

import javax.xml.bind.annotation.adapters.XmlAdapter;
import java.time.LocalDate;

/**
 * Показывает как преобразовывать объект класса LocalDate в строку.
 * Используется JAXB-ом, при сохранении и восстановлении коллекции в/из файл(-а).
 */
public class LocalDateAdapter extends XmlAdapter<String, LocalDate> {
    public LocalDate unmarshal(String v) {
        return LocalDate.parse(v);
    }

    public String marshal(LocalDate v) {
        return v.toString();
    }
}