package space.grow;

import space.Locationable;

@FunctionalInterface
public interface Growable {
    String getName();
    default void grow(Locationable where){
        System.out.println(this.getName() + " растут "  + where.getName());
    }

}
