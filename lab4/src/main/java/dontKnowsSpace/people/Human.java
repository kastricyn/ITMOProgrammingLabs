package dontKnowsSpace.people;

import java.util.Objects;

public abstract class Human implements Moveable, Lookable {
    private static int count;
    {
        count++;
    }
    public static int getCount() {
        return count;
    }


    private String name;
    public String getName() {
        return name;
    }

    public Human setName(String name) {
        this.name = name;
        return this;
    }

    public String sit(Object when){return "сидит на(в)" + when;}


    @Override
    public String toString() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Human)) return false;
        Human human = (Human) o;
        return Objects.equals(getName(), human.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName());
    }
}
