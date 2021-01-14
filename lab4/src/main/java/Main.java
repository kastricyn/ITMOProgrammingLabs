import exceptions.CantPokeException;
import exceptions.MinMoreThenMaxException;
import general.Color;
import general.Size;
import people.ActionStatus;
import people.Shorty;
import space.House;
import space.Location;
import space.grow.*;

import java.util.Arrays;

public class Main {
    static final int MIN_OBJ_ON_SEEDBED_NUMBER = 5, MAX_OBJ_ON_SEEDBED_NUMBER = 11,
            MIN_SEEDBEDS_NUMBER = 4, MAX_SEEDBEDS_NUMBER = 10,
            MIN_FLOWERBEDS_NUMBER = 6, MAX_FLOWERBEDS_NUMBER = 10,
            MIN_FLOWERS_ON_FLOWERBED_NUMBER = 3, MAX_FLOWERS_ON_FLOWERBED_NUMBER = 5,
            MIN_SHORTY_NUMBER = 2, MAX_SHORTY_NUMBER = 5;

    public static void main(String[] args) {

        try {
            Seedbed[] seedbeds = Seedbed.getRandomSeedbeds(MIN_SEEDBEDS_NUMBER, MAX_SEEDBEDS_NUMBER, MIN_OBJ_ON_SEEDBED_NUMBER, MAX_OBJ_ON_SEEDBED_NUMBER);
            for (int i = 0; i < seedbeds.length; i++) {
                if (seedbeds[i].getCropType().equals(CollectedPlant.Type.CUCUMBERS) ||
                        seedbeds[i].getCropType().equals(CollectedPlant.Type.TOMATOES))
                    seedbeds[i].cultivate();
            }
            System.out.println("грядки с" + CollectedPlant.Type.CUCUMBERS + " и " + CollectedPlant.Type.TOMATOES + " закончились");
            System.out.println("начало обработки грядок с " + CollectedPlant.Type.MOON_STRAWBERRY);
            //        Несколько коротышек
            int workerNumber = (int) (MIN_SHORTY_NUMBER + Math.random() * (MAX_SHORTY_NUMBER - MIN_SHORTY_NUMBER));
            Shorty[] workers = new Shorty[workerNumber];
            for (int i = 0; i < workerNumber; i++)
                workers[i] = new Shorty("worker-" + i);


            for (int i = 0; i < seedbeds.length; i++)
                if (seedbeds[i].getCropType().equals(CollectedPlant.Type.MOON_STRAWBERRY))
                    for (CollectedPlant p : seedbeds[i].getCrop())
                        if (p.isRipe()) {
                            int workerIdx = (int) (Math.random() * workerNumber);
                            workers[workerIdx].crawl(seedbeds[i]);
                            workers[workerIdx].collected(p);
                        }
        } catch (MinMoreThenMaxException e) {
            System.out.println(e);
        }


        Shorty fix = new Shorty("Фикс");
        Shorty dontKnow = new Shorty("Незнайка");

        Shorty someShorty = new Shorty("worker-some");
        someShorty.see(fix);
        someShorty.see(dontKnow);
        someShorty.shout("что-то");

        try {
            fix.poke(dontKnow, "метла");
        } catch (CantPokeException cpe) {
            System.out.println(cpe);
        }
        Location hill = () -> "холм";
        fix.go(hill);
        dontKnow.go(hill);

        House house = new House("большой красивый дом").setFloorNumber(2);
        House.Veranda veranda = house.new Veranda("большая открытая веранда");
        dontKnow.see(house);
        dontKnow.see(veranda);

        try {
            Flowerbed[] flowerbeds = Flowerbed.getRandomFlowerbeds(MIN_FLOWERBEDS_NUMBER, MAX_FLOWERBEDS_NUMBER, MIN_FLOWERS_ON_FLOWERBED_NUMBER, MAX_FLOWERS_ON_FLOWERBED_NUMBER);
            House.Environment environment = house.new Environment(Arrays.asList(flowerbeds));
            System.out.println(environment);
        } catch (MinMoreThenMaxException e) {
            System.out.println(e);
        }
        Growing lilac = () -> "кусты лунной сирени";
        lilac.grow(() -> "под окнами");
        Flower justFlower = new Flower("любое растение на Луне");
        System.out.println(justFlower);

        dontKnow.adapt("размер растений на Луне " + justFlower.getSizeRelativeEarth() + " по сравнению с растениями на Земле", ActionStatus.START);
        dontKnow.surprise("размер растений на Луне " + justFlower.getSizeRelativeEarth() + " по сравнению с растениями на Земле", ActionStatus.NOT);

        Shorty klops = new Shorty("господин Клопс").setWeight(Size.LARGE);
        klops.setFace(klops.new Face().setBaldHead(Size.BIG).setColorCheeks(Color.RED)
                .setColorHair(Color.PINK).setWeightEyes(Size.TINY).setEyebrows(Size.TINY));
        klops.sit(veranda);
        klops.printFullDescription();

        klops.sit(() -> "стол");
        klops.doing("4 дела", ActionStatus.IMMEDIATELY);

    }
}
