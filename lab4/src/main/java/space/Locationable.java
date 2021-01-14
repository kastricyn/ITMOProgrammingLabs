package space;

@FunctionalInterface
public interface Locationable {
    String getName();
    default boolean isLocation(){return true;}

}
