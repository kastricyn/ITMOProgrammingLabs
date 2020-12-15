package ru.ifmo.se.programming.kastricyn.People.Face;

import ru.ifmo.se.programming.kastricyn.Size;

public class Eyes {
    private Size size = Size.EMPTY;
    private String name = "глаза";
    private Object similar = null;

    public Eyes setSize(Size size) {
        this.size = size;
        return this;
    }

    public Size getSize() {
        return size;
    }

    public Eyes setSimilar(Object similar) {
        this.similar = similar;
        return this;
    }

    public Object getSimilar() {
        return similar;
    }


    public Eyes setName(String name) {
        this.name = name;
        return this;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        String answ = size.toString() + " " + name;
        if(similar!=null)
            answ +=", словно " + similar.toString().replaceFirst("а$", "и") + ",";
        return answ;
    }
}
