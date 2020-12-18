package ru.ifmo.se.programming.kastricyn;

import java.util.Objects;

public final class AllStockCompany {
    private static int count = 0;
    public static void addStockCompany(){count++;}
    private String name = "все акционерные общества и компании";

    public static String createFor(ReasonForCreate reason, ReasonForCreate reasonSaySimply ){
        String answ = "устраивались для " + reason.toString() + ", или, говоря проще, " + reasonSaySimply.toString();
        return answ;
    }

    public static String String(){
        return "все акционерные общества и компании";
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AllStockCompany)) return false;
        AllStockCompany that = (AllStockCompany) o;
        return Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
