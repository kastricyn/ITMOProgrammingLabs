package ru.ifmo.se.programming.kastricyn.People;

import javax.lang.model.element.TypeParameterElement;

public class All extends Human {
    public String toString(TypeAll typeAll) {
        return typeAll.toString();
    }

    public String seem(String verb, Object thing, Object reason) {
        return "благодаря " + reason.toString() + (toString() + "м") +
                " " + verb + ", будто " + thing.toString();
    }
    @Override
    public String toString() {
        return TypeAll.ALL.toString();
    }
}
