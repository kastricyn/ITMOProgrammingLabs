package people;

import space.Location;
import space.View;

public interface IPeople {
    String getName();

    void setName(String name);

    default String have(String verb, Object thing) {
        return (verb + " " + thing);
    }

    default void see(View view) {
        System.out.println(this.getName() + " увидел " + view);
    }

    default void sit(Location where) {
        System.out.println(this.getName() + " сидит на(в)" + where);
    }
//todo переделать для s.asList
    default void see(View view, ActionStatus s) {
        System.out.println(this.getName() + " " + s + " увидел " + view);
    }

    default void doing(String thing, ActionStatus ... ss){
        String str = " ";
        for (ActionStatus s:ss)
            str+=s+" ";
        System.out.println(this.getName() + str + "делает "+ thing);
    }
//  move abilities

    default String appear(String verb) {
        return verb;
    }

    default void go(Location where) {
        System.out.println(this.getName() + " идёт на(в) " + where);
    }
//todo  переделать для s.asList
    default void go(Location where, ActionStatus s) {
        System.out.println(this.getName() + " " + s + " идёт " + where);
    }

    //mind abilities
    default String think(String verb, Object thing) {
        return (verb + " " + thing);
    }

    default String know(String verb, Object thing) {
        return (verb + ", что " + thing);
    }

    default String interested(String verb, Object thing) {
        return (verb + " " + thing);
    }

    default String sure(String verb, Object thing) {
        return (verb + ", что " + thing);
    }

    //todo  переделать для s.asList
    default String adapt(String verb, Object thing) {
        return (verb + ", что " + thing);
    }
    default String surprise(String verb, Object thing) {
        return (verb + ", что " + thing);
    }

}
