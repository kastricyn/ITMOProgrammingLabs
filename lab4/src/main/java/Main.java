import exceptions.CantPokeException;
import people.ActionStatus;
import people.Shorty;
import space.Location;
import space.View;

public class Main {
    static final int MIN_SEEDBEDS_NUMBER = 5, MAX_SEEDBEDS_NUMBER = 11,
                     MIN_SHORTY_NUMBER = 3, MAX_SHORTY_NUMBER = 5;

    public static void main(String[] args) {

//грядки с огурцами и помидорами
        int seedbedsNumber = (int) (MIN_SEEDBEDS_NUMBER + Math.random() * (MAX_SEEDBEDS_NUMBER - MIN_SEEDBEDS_NUMBER));


//Обработка грядок огурцами и помидорами, начало обработки грядок с лунной клубникой

//        Несколько коротышек
        int shortyNumber = (int) (MIN_SHORTY_NUMBER + Math.random() * (MAX_SHORTY_NUMBER - MIN_SHORTY_NUMBER));

        Shorty fix = new Shorty("Фикс");
        Shorty dontKnow = new Shorty("Незнайка");

//        Несколько коротышек ползали среди грядок и собирали созревшую клубнику,
//                складывая ее в круглые плетеные корзины.
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

        dontKnow.see(new View(){        //Todo: заменить на вид принадлежащий Location.HILL со всеми обектами
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

    Shorty klops = new Shorty("Господин Клопс"){
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

        klops.sit(Location.TABLE);
        klops.doing("4 дела", ActionStatus.IMMEDIATELY);

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


        //todo: удалить неиспользуемое
    }
}
