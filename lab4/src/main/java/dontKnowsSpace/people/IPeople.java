package dontKnowsSpace.people;

public interface IPeople {
    String getName();

    void setName(String name);

    default String think(String verb, Object thing) {
        return (verb + " " + thing.toString());
    }

    default String know(String verb, Object thing) {
        return (verb + ", что " + thing.toString());
    }

    default String interested(String verb, Object thing) {
        return (verb + " " + thing.toString());
    }

    default String sure(String verb, Object thing) {
        return (verb + ", что " + thing.toString());
    }

    default String appear(String verb) {
        return verb;
    }

    default String have(String verb, Object thing) {
        return (verb + " " + thing.toString());
    }

}
