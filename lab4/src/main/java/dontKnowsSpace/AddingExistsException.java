package dontKnowsSpace.exсeptions;

import dontKnowsSpace.place.GrowPlace;

import java.util.Arrays;

public class AddingExistsException extends Exception{
    //todo: delete this exception
    GrowPlace.GrowType[] types;

    public AddingExistsException (GrowPlace.GrowType[] types){
        this.types = types;
    }

    @Override
    public String toString() {
        String ans = "Следующие типы уже могут расти: ";
        for (GrowPlace.GrowType t:
             types) {
            ans += t + ", ";
        }
        return ans.replaceFirst(", $", ".");
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AddingExistsException)) return false;
        AddingExistsException that = (AddingExistsException) o;
        return Arrays.equals(types, that.types);
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(types);
    }
}
