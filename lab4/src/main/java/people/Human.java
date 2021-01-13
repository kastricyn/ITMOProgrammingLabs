package people;

import java.util.Objects;

public abstract class Human implements IPeople{
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

    public void setName(String name) {
        this.name = name;
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
