package dontKnowsSpace.people;

public interface Lookable {
    default String see(Object inView) {
        return "увидеть " + inView;
    }
}
