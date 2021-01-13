import dontKnowsSpace.exсeptions.CantPokeException;
import dontKnowsSpace.people.Shorty;
import dontKnowsSpace.place.GrowPlace;
import dontKnowsSpace.place.Seedbed;
import dontKnowsSpace.place.Seedbeds;

import java.util.ArrayList;

public class Main {
    static final int MIN_SEEDBEDS_NUMBER = 5, MAX_SEEDBEDS_NUMBER = 11,
                     MIN_SHORTY_NUMBER = 3, MAX_SHORTY_NUMBER = 5;

    public static void main(String[] args) {

//грядки с огурцами и помидорами
        int seedbedsNumber = (int) (MIN_SEEDBEDS_NUMBER + Math.random() * (MAX_SEEDBEDS_NUMBER - MIN_SEEDBEDS_NUMBER));
        ArrayList<Seedbed> seedbeds = new ArrayList<>();
        for (int i = 0; i < seedbedsNumber; i++)
            seedbeds.add(Seedbeds.getSeedbedWithRandomTypeObj("грядка " + i, GrowPlace.GrowType.CUCUMBERS, GrowPlace.GrowType.TOMATOES));

//Обработка грядок огурцами и помидорами, начало обработки грядок с лунной клубникой
        Seedbeds.cultivate(seedbeds, new GrowPlace.GrowObj("огурцы"));
        Seedbeds.cultivate(seedbeds, new GrowPlace.GrowObj("помидоры"));
        Seedbeds.startCultivate(seedbeds, new GrowPlace.GrowObj("лунная клубника"));

// TODO: delete it:
//
//        Shorty someShorty = new Shorty("Несколько коротышек") {
//            @Override
//            public String toString() {
//                return getName();
//            }
//            @Override
//            public void getToWork(){
//                System.out.println(this + "принялись за работу");
//            }
//        };
//
//        System.out.println(someShorty);

//        Несколько коротышек
        int shortyNumber = (int) (MIN_SHORTY_NUMBER + Math.random() * (MAX_SHORTY_NUMBER - MIN_SHORTY_NUMBER));
        ArrayList<Shorty> shorties = new ArrayList<>();
        for (int i = 0; i < seedbedsNumber; i++)
            shorties.add(new Shorty("Коротышка-рабочий " + i))



        Shorty fix = new Shorty("Фикс");
        Shorty dontKnow = new Shorty("Незнайка");

        fix.getToWork();

//        Несколько коротышек ползали среди грядок и собирали созревшую клубнику,
//                складывая ее в круглые плетеные корзины.
//                Один из работавших коротышек увидел Фикса с Незнайкой и закричал:
//        Коротышки молча принялись за работу.
        try {
            fix.poke(dontKnow, "метла");
        } catch (CantPokeException cpe) {
            System.out.println(cpe);
        }


//                Поднявшись на холм, Незнайка увидел красивый двухэтажный дом с большой открытой верандой.
//        Вокруг дома были разбиты клумбы с цветами.
//                Здесь были и лунные маргаритки, и анютины глазки, и настурции, и лунная резеда, и астры.
//                Под окнами дома росли кусты лунной сирени.
//        Все эти цветы были такие же, как и у нас на Земле, только во много раз мельче.
//        Впрочем, Незнайка уже начал привыкать к тому, что на Луне растения маленькие, и это уже не удивляло его.
//                На веранде сидел господин Клопс.
//        Это был толстенький краснощекенький коротышка с большой розовой лысиной на голове.
//                Глазки у него были узенькие как щелочки, а бровей почти совсем не было, отчего лицо его казалось очень веселым и добрым.
//                Одет он был в просторную шелковую пижаму темно-коричневого цвета с белыми полосочками и шлепанцы на ногах.
//        Он сидел за столом и делал сразу четыре дела:


//       TODO: Delete it:
//
//        dontKnowsSpace.place.Seedbed a = new dontKnowsSpace.place.Seedbed("грядка 1");
//        for (int i = 0; i<2; i++){
//        try {
//            a.Cultivate();
//        }
//        catch (CultivatedException ce){
//            System.out.println(a + " уже была обработана");
//        }}

    }
}
