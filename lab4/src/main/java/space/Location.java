package space;

@FunctionalInterface
public interface Location {
    String getName();

    default boolean isLocation() {
        return true;
    }
}
