package space;


public class House implements Locationable {
    String name;
    int floorNumber = 1;

    public House(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public int getFloorNumber() {
        return floorNumber;
    }


    public void setName(String name) {
        this.name = name;
    }


    public House setFloorNumber(int floorNumber) {
        if (floorNumber > 0)
            this.floorNumber = floorNumber;
        else return null;
        return this;
    }

    public class Veranda implements Locationable {
        private String name;

        public Veranda(String name){
            this.name = name;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return name + " от " + House.this.getName();
        }
    }

    public class Environment implements Locationable {
        Object[] objects;

        public Environment(Object ... objs){
            objects = objs;
        }

        @Override
        public String toString() {
            String ans =  "Окружение " + House.this.getName()  +":\n ";
            for (int i = 0; i < objects.length-1; i++) {
                ans += "\t" + objects[i] + ",\n";
            }
            ans+="\t" +objects[objects.length-1];
            return ans;
        }

        @Override
        public String getName() {
            return "Окружение " + House.this.getName();
        }
    }

    @Override
    public String toString() {
        return  name + ":\n" +
                "\tкол-во этажей:" + floorNumber;
    }
}
