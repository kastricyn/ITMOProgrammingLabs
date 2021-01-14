package space;

import java.util.Arrays;
import java.util.Objects;

public class House implements Location {
    private String name;
    private int floorNumber = 1;
    private Veranda veranda;
    private Environment environment;

    public House(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public int getFloorNumber() {
        return floorNumber;
    }

    public Veranda getVeranda() {
        return veranda;
    }

    public Environment getEnvironment() {
        return environment;
    }

    public House setName(String name) {
        this.name = name;
        return this;
    }

    public House setVeranda(Veranda veranda) {
        this.veranda = veranda;
        return this;
    }

    public House setEnvironment(Environment environment) {
        this.environment = environment;
        return this;
    }

    public House setFloorNumber(int floorNumber) {
        if (floorNumber > 0)
            this.floorNumber = floorNumber;
        else return null;
        return this;
    }

    @Override
    public String toString() {
        return name + ":\n" +
                "\tкол-во этажей:" + floorNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof House)) return false;
        House house = (House) o;
        return getFloorNumber() == house.getFloorNumber() && Objects.equals(getName(), house.getName()) && Objects.equals(getVeranda(), house.getVeranda()) && Objects.equals(getEnvironment(), house.getEnvironment());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getFloorNumber(), getVeranda(), getEnvironment());
    }

    public class Environment implements Location {
        Object[] objects;

        public Environment(Object... objs) {
            objects = objs;
        }

        public String getName() {
            return "Окружение " + House.this.getName();
        }

        @Override
        public String toString() {
            String ans = "Окружение " + House.this.getName() + ":\n ";
            for (int i = 0; i < objects.length - 1; i++) {
                ans += "\t" + objects[i] + ",\n";
            }
            ans += "\t" + objects[objects.length - 1];
            return ans;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof Environment)) return false;
            Environment that = (Environment) o;
            return Arrays.equals(objects, that.objects);
        }

        @Override
        public int hashCode() {
            return Arrays.hashCode(objects);
        }
    }

    public class Veranda implements Location {
        private String name;

        public Veranda(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

        public Veranda setName(String name) {
            this.name = name;
            return this;
        }

        @Override
        public String toString() {
            return name + " от " + House.this.getName();
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof Veranda)) return false;
            Veranda veranda = (Veranda) o;
            return Objects.equals(getName(), veranda.getName());
        }

        @Override
        public int hashCode() {
            return Objects.hash(getName());
        }
    }

}
