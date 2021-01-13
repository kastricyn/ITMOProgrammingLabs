package dontKnowsSpace.place;

import dontKnowsSpace.exсeptions.CultivatedException;

import java.util.ArrayList;

public final class Seedbeds {
    public static Seedbed getSeedbedWithRandomTypeObj(String name, dontKnowsSpace.place.GrowPlace.GrowType ... growTypes){
        ArrayList<dontKnowsSpace.place.GrowPlace.GrowType> res = new ArrayList<>();
        for (dontKnowsSpace.place.GrowPlace.GrowType t:
                growTypes) {
            if(Math.random()<0.5 && !res.contains(t))
                res.add(t);
        }
        return new Seedbed(name, (dontKnowsSpace.place.GrowPlace.GrowType[]) res.toArray());
    }

    static void cultivate(ArrayList<Seedbed> seedbeds) {
        for (Seedbed s :
                seedbeds) {
            try {
                s.cultivate();
            } catch (CultivatedException ce) {
                System.out.println(s.getName() + "> уже была обработана");
            }
        }
    }

    public static void cultivate(ArrayList<Seedbed> seedbeds, dontKnowsSpace.place.GrowPlace.GrowType type) {
        System.out.println("Начало обработки грядок, где растут(ёт) " + type);
        ArrayList<Seedbed> t = new ArrayList<>();
        for (Seedbed s : seedbeds)
            if ()
                t.add(s);
        cultivate(t);
        System.out.println("Грядки, где растут(ёт) " + obj.getName() + " закончились.");
    }

    public static void startCultivate(ArrayList<Seedbed> seedbeds, dontKnowsSpace.place.GrowPlace.GrowObj obj) {
        System.out.println("Начало обработки грядок, где растут(ёт) " + obj.getName());
     }


}
