package ru.ifmo.se.programming.kastricyn.People;

import ru.ifmo.se.programming.kastricyn.Size;

public class RichPerson extends Person{
    private Size size = Size.EMPTY;
    {
        setName("богач");
    }

    public void setSize(Size size) {
        this.size = size;
    }

    @Override
    public String toString() {
        return size.toString() +" "+ getName();
    }

}
