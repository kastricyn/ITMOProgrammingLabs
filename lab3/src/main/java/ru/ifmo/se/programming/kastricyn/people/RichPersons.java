package ru.ifmo.se.programming.kastricyn.people;

import ru.ifmo.se.programming.kastricyn.Size;

public class RichPersons extends Persons {
    private Size size = Size.EMPTY;

    {
        setName("богачи");
    }

    public void setSize(Size size) {
        this.size = size;
    }

    @Override
    public String toString() {
        return size.toString().replaceFirst("й", "е") + " " + getName();
    }
}
