package dontKnowsSpace.people;

import java.lang.reflect.Constructor;
import java.lang.reflect.Parameter;

public class ObjectFactory {
    public ObjectFactory(String className) throws ClassNotFoundException {
        Class shortyClass = Class.forName(className);
        Constructor[] constructors = shortyClass.getConstructors();
        Constructor constr = constructors[0];
        for (Constructor c : constructors) {
            if (c.getParameterCount() > constr.getParameterCount())
                constr = c;
        }

        Parameter[] parametres = constr.getParameters();


    }
}
