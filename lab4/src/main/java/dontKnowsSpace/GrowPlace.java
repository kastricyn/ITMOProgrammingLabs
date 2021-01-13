package dontKnowsSpace.place;

import java.util.Arrays;
import java.util.Objects;

public abstract class GrowPlace {
    private String name = "место для выращивания";
    private GrowType[] growTypes = GrowType.values();
//    TODO: delete:
//     private ArrayList<GrowObj> objectsOfCultivations = new ArrayList<>();


    //TODO: delete it:

//    public void addObjectOfCultivation(GrowObj obj) throws AddingExistsException {
//        if (objectsOfCultivations.contains(obj))
//            throw new AddingExistsException();
//        objectsOfCultivations.add(obj);
//    }



//    public GrowObj getObjectOfCultivation(int idx) {
//        return objectsOfCultivations.get(idx);
//    }
//
//    public GrowPlace removeObjectOfCultivation(GrowObj obj) {
//        this.objectsOfCultivations.remove(obj);
//        return this;
//    }
//
//    public int getNumberOfObjectsOfCultivation() {
//        return objectsOfCultivations.size();
//    }
//
//    public ArrayList<GrowObj> getObjectsOfCultivations() {
//        return (ArrayList<GrowObj>) objectsOfCultivations.clone();
//    }

    public GrowPlace() {}

    public GrowPlace(String name) {
        this.name = name;
    }
    public GrowPlace(String name, GrowType[] types) {
        this.name = name;
        this.growTypes = types;
    }


    public GrowType[] getGrowTypes() {
        return growTypes;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        String answ = name + ", где ";
        switch (growTypes.length) {
            case 0:
                answ += "ничего не растёт";
                break;
            case 1:
                answ += "растёт только " + growTypes[0];
                break;
            default:
                String t = "растут ";
                for (int i = 0; i < growTypes.length - 1; i++)
                    t += growTypes[i] + ", ";
                t += growTypes[growTypes.length-1] + ".";
                answ += t;
                break;
        }
        return answ;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof GrowPlace)) return false;
        GrowPlace growPlace = (GrowPlace) o;
        return Objects.equals(getName(), growPlace.getName()) && Arrays.equals(growTypes, growPlace.growTypes);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(getName());
        result = 31 * result + Arrays.hashCode(growTypes);
        return result;
    }

    public static class GrowObj {
        private String name = "none";
        private GrowType type;
        private boolean isRipe;

        public GrowObj(GrowType type) {
            this.type = type;
        }

        public GrowObj(String name, GrowType type) {
            this.name = name;
            this.type = type;
        }

        public String getName() {
            return name;
        }

        public GrowType getType() {
            return type;
        }

        public boolean getIsRipe(){return isRipe;}

        @Override
        public String toString() {
            return "Объект выращивания: \n" +
                    "\t тип: " + type + "\n" +
                    "\t имя: " + name + "\n" +
                    "\t зрелый: " + (isRipe?"да":"нет") ;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof GrowObj)) return false;
            GrowObj growObj = (GrowObj) o;
            return isRipe == growObj.isRipe && Objects.equals(getName(), growObj.getName()) && type == growObj.type;
        }

        @Override
        public int hashCode() {
            return Objects.hash(getName(), type, isRipe);
        }
    }

    public enum GrowType{
        CUCUMBERS("огурцы"),
        TOMATOES("помидоры"),
        MOON_STRAWBERRY("лунная клубника"),
        ;
        private String name;

        GrowType(String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return name;
        }
    }
}
