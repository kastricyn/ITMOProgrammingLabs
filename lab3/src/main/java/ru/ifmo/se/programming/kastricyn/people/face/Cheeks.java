package ru.ifmo.se.programming.kastricyn.people.face;

public class Cheeks {
    private String name;
    private String count;
    private String bulk;

    public Cheeks setName(String name) {
        this.name = name;
        return this;
    }

    public String getName() {
        return name;
    }

    public String getBulk() {
        return bulk;
    }


    public Cheeks setBulk(String bulk) {
        this.bulk = bulk;
        return this;
    }


    public String getCount() {
        return count;
    }

    public Cheeks setCount(String count) {
        this.count = count;
        return this;
    }


    @Override
    public String toString() {
        return count + " " + bulk + " " + name;
    }
}
