package dontKnowsSpace.people;

public interface Moveable {
    default String go(String where) {
        return "идти " + where;
    }

    default String go(String verbForm, String where) {
        return verbForm + where;
    }

    default String beUp(Object where) {
        return "подняться " + where;
    }

}
