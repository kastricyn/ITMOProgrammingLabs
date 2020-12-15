package ru.ifmo.se.programming.kastricyn.People;

import ru.ifmo.se.programming.kastricyn.Saleable;

public interface IPeople {
    String getName();
    void setName(String name);

    default String think(String verb, Object thing){
        return (verb + " " + thing.toString());
    }
    default String know (String verb, Object thing){
        return (verb + ", что " + thing.toString());
    }
    default String interested(String verb, Object thing) {
        return (verb + " "+  thing.toString());
    }

    default String sure (String verb, Object thing) {
        return (verb + ", что "+  thing.toString());
    }

    default String appear(String verb){
        return verb;
    }

    default String have (String verb, Object thing) {
        return (verb + " "+  thing.toString());
    }

    default String talk(String verb, Person interlocouter){
        return verb + " c " + interlocouter.toStringWithoutAmbassador().replaceFirst(" ", "ом ") +"ом";
    }
}
