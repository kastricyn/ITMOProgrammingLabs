package people;


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


    @Override
    public String toString() {
        return name;
    }


}
