package ru.ifmo.se.programming.kastricyn;

import ru.ifmo.se.programming.kastricyn.people.*;
import ru.ifmo.se.programming.kastricyn.people.face.Cheeks;
import ru.ifmo.se.programming.kastricyn.people.face.Eyes;
import ru.ifmo.se.programming.kastricyn.people.face.Face;
import ru.ifmo.se.programming.kastricyn.people.face.Nose;

public class Main {
    public static void main(String[] args) {
        Stocks stocks = new Stocks();
//      Какие-то люди очень охотно платили деньги за акции.
        SomePeople people = new SomePeople();
        System.out.println(people.toString() + " " + people.pay("деньги", stocks, "платили", ActionStatus.GLADLY) + ".");

//      Жители Давилона покупают акции. Приезжие из других городов покупают акции.
        Persons inhebitantDavilons = new Persons();
        inhebitantDavilons.setHomeCity(City.Davilon);
        System.out.println(inhebitantDavilons.inhabitantCity(City.Davilon) + " " +
                inhebitantDavilons.buy("покупают", stocks));
        Persons guestDavilons = new Persons();
        System.out.println(guestDavilons.inhabitantCity(City.Davilon) + " " +
                guestDavilons.buy("покупают", stocks));

//       Все интересуются акциями,
        All all = new All();
        System.out.print(all.toString() + " " + all.interested("интересуются", stocks).replaceFirst("и$", "ями"));
//       кроме крупных богачей
        RichPersons richPersons = new RichPersons();
        richPersons.setSize(Size.LARGE);
        System.out.print(", кроме " + richPersons.toString().replaceFirst("е ", "х ").replaceFirst("и$", "ей"));

//      , уверенных что Общество гигантских растений -- это обычное акционерное общество, которое вскорости лопнет и прекратит свое существование.
        StockCompany GPS = new StockCompany();
        GPS.setName("Общество гигантских растений");
        System.out.println(richPersons.sure(", уверенных", GPS.toString() +
                " -- это " + new StockCompany().toString() + ", которое " +
                GPS.burst(ActionStatus.SOON).replaceFirst("уть", "ет") +
                " и " + GPS.stopTheExistence().replaceFirst("ить", "ит")));
//        Богачи прекрасно знали, что все акционерные общества и компании устраивались лишь для прикарманивания чужих денег ( = облапошивания бедняков).
        System.out.println(richPersons.toString() + " " + ActionStatus.GOODLY +
                richPersons.know(" знали", AllStockCompany.String()) + " " +
                AllStockCompany.createFor(ReasonForCreate.INTO_POCKET, ReasonForCreate.SWINDLING_POOR));

        //        Господин Спрутс – типичный представитель богатейших жителей Грабенберга, не отличающиеся большой красотой – объявился и заинтересовался гигантскими акциями.
        Persons inhabitantGrabenberg = new Persons();
        inhabitantGrabenberg.setHomeCity(City.Grabenberg);
        Person spruts = new Person();
        spruts.setAccost("господин");
        spruts.setLastName("Спрутс");
        spruts.setHomeCity(City.Grabenberg);
        spruts.setAmbassador("- типичный представитель богатейших жителей города " + inhabitantGrabenberg.getHomeCity() + "a");
        System.out.println(spruts.toString() + ", " + inhabitantGrabenberg.differ("не отличающихся ", Size.BIG, " красотой") +
                " - " + spruts.appear("объявился") + " и " + spruts.interested("заинтересовался ",
                Size.GIGANTIC.toString().replaceFirst("ий", "ими") + " " + stocks.getName().replaceFirst("и$", "ями")));

        //      Спрутс имел широковатое лицо с малюсенькими глазками, словно гвоздики, и чрезвычайно тоненьким, зажатым между двумя пухлыми щечками носиком.
        spruts.setFace(new Face(
                new Eyes().setName("глазки").setSize(Size.TINY).setSimilar(new Flower("гвоздика")),
                new Nose().setWeight("чрезвычайно тоненький").setName("носик"),
                new Cheeks().setBulk("пухлые").setCount("две").setName("щечки")
        ).setWeight("широковатое"));

        System.out.println(
                spruts.getLastName() + " " +
                        spruts.have("имел", spruts.getFace().toString())
        );
//    Благодаря ширине лица и некоторой припухлости щек всем казалось, будто господин Спрутс постоянно улыбается, и это придавало ему смешной вид.

        System.out.print(
                all.seem("казалось", spruts.toStringWithoutAmbassador() + " " +
                                spruts.smile(ActionStatus.PERMANENTLY)
                        , "ширине лица и припухлости щек"));
        System.out.println(", и это придавало ему " + new View().setWhichView("смешной"));

        //Никто не думал смеяться над ним.
        System.out.println(all.toString(TypeAll.NOT_NOTHING) + " " + all.think("не думал", "смеяться над ним"));


//     Каждый, кто разговаривал с господином Спрутсом, думал исключительно о его богатстве.
        System.out.println(all.toString(TypeAll.EVERY_WHO) + " " + all.talk("разговаривал", spruts) + ", " +
                all.think("думал", ActionStatus.ONLY.toString() + " о его богатстве"));


    }
}
