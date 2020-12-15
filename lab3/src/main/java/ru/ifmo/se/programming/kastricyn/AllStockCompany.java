package ru.ifmo.se.programming.kastricyn;

public final class AllStockCompany {
    private static int count = 0;
    public static void addStockCompany(){count++;}
    private String name = "все акционерные общества и компании";

    public static String createFor(reasonForCreate reason, reasonForCreate reasonSaySimply ){
        String answ = "устраивались для " + reason.toString() + ", или, говоря проще, " + reasonSaySimply.toString();
        return answ;
    }

    public static String String(){
        return "все акционерные общества и компании";
    }

    public static enum reasonForCreate{
        INTO_POCKET{
            @Override
            public String toString() {
                return "прикарманивания чужих денег";
            }
        },
        SWINDLING_POOR{
            @Override
            public String toString() {
                return "облапошивания бедняков";
            }
        };

    }
}
