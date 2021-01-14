package people;

import space.Locationable;

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

    default void sit(Locationable where, ActionStatus... ss) {
        String str = " ";
        for (ActionStatus s : ss)
            str += s + " ";
        System.out.println(this.getName() + str + "сидит на(за) " + where.getName());
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

    default void go(Locationable where, ActionStatus... ss) {
        String str = " ";
        for (ActionStatus s : ss)
            str += s + " ";
        System.out.println(this.getName() + str + "идёт в(на) " + where.getName());
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

    default void adapt(String fact, ActionStatus... ss) {
        String str = " ";
        for (ActionStatus s : ss)
            str += s + " ";
        System.out.println(this.getName() + str + "привыкать, что " + fact);
    }

    default void surprise(String fact, ActionStatus... ss) {
        String str = " ";
        for (ActionStatus s : ss)
            str += s + " ";
        System.out.println("для " + this.getName() + str + "сюрприз, что " + fact);
    }

}
