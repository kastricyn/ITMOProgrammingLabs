package people;

import space.Location;

public interface IPeople {
    String getName();

    default String getStr(ActionStatus ... ss){
        String str = this.getName() + " ";
        for (ActionStatus s : ss)
            str += s + " ";
        return str;
    }

    default void adapt(String fact, ActionStatus... ss) {
        System.out.println(getStr(ss) + "привыкать, что " + fact);
    }

    default void doing(String thing, ActionStatus... ss) {
        System.out.println(getStr(ss) + "делает " + thing);
    }

    default void go(Location where, ActionStatus... ss) {
        System.out.println(getStr(ss) + "идёт в(на) " + where.getName());
    }

    default void see(Object view, ActionStatus... ss) {
        System.out.println(getStr(ss) + "увидел " + view);
    }

    default void sit(Location where, ActionStatus... ss) {
        System.out.println(getStr(ss) + "сидит на(за) " + where.getName());
    }

    default void shout(String phrase, ActionStatus... ss) {
        System.out.println(getStr(ss) + "кричит " + phrase);
    }

    default void surprise(String fact, ActionStatus... ss) {
        System.out.println("для " + getStr(ss) + "сюрприз, что " + fact);
    }

}
