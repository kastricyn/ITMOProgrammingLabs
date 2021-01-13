package people;

import space.Location;

public interface IPeople {
    String getName();

    void setName(String name);

    default String have(String verb, Object thing) {
        return (verb + " " + thing);
    }

    default void see(Object view, ActionStatus... ss) {
        String str = " ";
        for (ActionStatus s : ss)
            str += s + " ";
        System.out.println(this.getName() + str + "увидел " + view);
    }

    default void sit(Location where, ActionStatus... ss) {
        String str = " ";
        for (ActionStatus s : ss)
            str += s + " ";
        System.out.println(this.getName() + str + "сидит на(в) " + where);
    }

    default void doing(String thing, ActionStatus... ss) {
        String str = " ";
        for (ActionStatus s : ss)
            str += s + " ";
        System.out.println(this.getName() + str + "делает " + thing);
    }

    default void shout(String phrase, ActionStatus... ss) {
        String str = " ";
        for (ActionStatus s : ss)
            str += s + " ";
        System.out.println(this.getName() + str + "кричит " + phrase);
    }
//  move abilities

    default String appear(String verb) {
        return verb;
    }

    default void go(Location where, ActionStatus... ss) {
        String str = " ";
        for (ActionStatus s : ss)
            str += s + " ";
        System.out.println(this.getName() + str + "идёт в(на) " + where);
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
