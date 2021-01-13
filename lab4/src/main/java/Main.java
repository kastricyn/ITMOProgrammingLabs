import exceptions.CantPokeException;
import exceptions.MinMoreThenMaxException;
import people.ActionStatus;
import people.Shorty;
import space.House;
import space.Location;
import space.View;
import space.grow.CollectedPlant;
import space.grow.Seedbed;

public class Main {
    static final int MIN_OBJ_ON_SEEDBED_NUMBER = 5, MAX_OBJ_ON_SEEDBED_NUMBER = 11,
            MIN_SHORTY_NUMBER = 2, MAX_SHORTY_NUMBER = 5;

    public static void main(String[] args) {

//грядки с огурцами и помидорами
        try {
            Seedbed[] seedbeds = Seedbed.getRandomArraySeedbeds(4, 10, MIN_OBJ_ON_SEEDBED_NUMBER, MAX_OBJ_ON_SEEDBED_NUMBER);
            for (int i = 0; i < seedbeds.length; i++) {
                if (seedbeds[i].getCropType().equals(CollectedPlant.Type.CUCUMBERS) ||
                        seedbeds[i].getCropType().equals(CollectedPlant.Type.TOMATOES))
                    seedbeds[i].cultivate();
            }
            System.out.println("грядки с огурцами и помидорами закончились");
            System.out.println("начало обработки грядок с лунной клубникой");
            //        Несколько коротышек
            int workerNumber = (int) (MIN_SHORTY_NUMBER + Math.random() * (MAX_SHORTY_NUMBER - MIN_SHORTY_NUMBER));
            Shorty[] workers = new Shorty[workerNumber];
            for (int i = 0; i < workerNumber; i++) {
                workers[i] = new Shorty("worker-" + i);
                workers[i].crawl(Location.SEEDBEDS);
            }


            for (int i = 0; i < seedbeds.length; i++) {
                if (seedbeds[i].getCropType().equals(CollectedPlant.Type.MOON_STRAWBERRY)) {
                    for (CollectedPlant p: seedbeds[i].getCrop()) {
                        if(p.isRipe())
                            workers[(int)(Math.random()*workerNumber)].collected(p);
                    }
                }


            }

        } catch (MinMoreThenMaxException e) {
            System.err.println("Возникла ошибка типа min > max.\n" +
                    "Проверьте правильность использования констант. \n" +
                    "До исправления полноценность работы программы не гарантируется.");
        }


        Shorty fix = new Shorty("Фикс");
        Shorty dontKnow = new Shorty("Незнайка");

//        Несколько коротышек ползали среди грядок и собирали созревшую клубнику,
//                складывая ее в круглые плетеные корзины.
        Shorty someShorty = new Shorty("worker-some");
        someShorty.see(fix);
        someShorty.see(dontKnow);
        someShorty.shout("");
//                Один из работавших коротышек увидел Фикса с Незнайкой и закричал:
//        Коротышки молча принялись за работу.
        try {
            fix.poke(dontKnow, "метла");
        } catch (CantPokeException cpe) {
            System.out.println(cpe);
        }

        fix.go(Location.HILL);
        dontKnow.go(Location.HILL);

//                Поднявшись на холм, Незнайка увидел красивый двухэтажный дом с большой открытой верандой.
//        Вокруг дома были разбиты клумбы с цветами.
//                Здесь были и лунные маргаритки, и анютины глазки, и настурции, и лунная резеда, и астры.
//                Под окнами дома росли кусты лунной сирени.
//        Все эти цветы были такие же, как и у нас на Земле, только во много раз мельче.

        House house = new House("большой красивый дом").setFloorNumber(2);
        House.Veranda veranda = house.new Veranda("большая открытая веранда");
        dontKnow.see(house);
        dontKnow.see(veranda);

        dontKnow.see(new View() {        //Todo: заменить на вид принадлежащий Location.HILL со всеми обектами
            @Override
            public String toString() {
                return "красивый двухэтажный дом с большой открытой верандой. <...>   " +
                        "Все эти цветы были такие же, как и у нас на Земле, только во много раз мельче.";
            }
        });

        //        Впрочем, Незнайка уже начал привыкать к тому, что на Луне растения маленькие, и это уже не удивляло его.
        dontKnow.adapt("размер растений на Луне маленький", ActionStatus.START);
        dontKnow.surprise("\"размер растений на Луне маленький\"", ActionStatus.NOT);
//                На веранде сидел господин Клопс.

        Shorty klops = new Shorty("Господин Клопс") {
            @Override
            public String toString() {
                return "Клопс - толстенький краснощекенький коротышка"
                        + "глаза: узенькие,"
                        + "брови: почти отсутствуют,"
                        + "лицо кажется очень весёлым и добрым,"
                        + "Одет он был в просторную шелковую пижаму темно-коричневого цвета с белыми полосочками и шлепанцы на ногах"
                        ;
            }
        };
        klops.sit(Location.VERANDA);
        System.out.println(klops);


//        Это был толстенький краснощекенький коротышка с большой розовой лысиной на голове.
//                Глазки у него были узенькие как щелочки, а бровей почти совсем не было, отчего лицо его казалось очень веселым и добрым.
//                Одет он был в просторную шелковую пижаму темно-коричневого цвета с белыми полосочками и шлепанцы на ногах.
//        Он сидел за столом и делал сразу четыре дела:
        klops.sit(Location.TABLE);
        klops.doing("4 дела", ActionStatus.IMMEDIATELY);

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


        //todo: удалить неиспользуемое
        //todo: заново переопределить hashCode() and equals()
    }
}
