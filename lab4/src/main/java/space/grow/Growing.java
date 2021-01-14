package space.grow;

import space.Location;

@FunctionalInterface
public interface Growing {
    String getName();

    default void grow(Location where) {
        System.out.println(this.getName() + " растут " + where.getName());
    }

}
