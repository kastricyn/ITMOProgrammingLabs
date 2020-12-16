package ru.ifmo.se.programming.kastricyn;

import java.util.Objects;

public class StockCompany {
    private String name = "обычное акционерное общество";

    public void setName(String name){this.name = name; }
    public String getName(){return name; }

    {AllStockCompany.addStockCompany();}
    public String burst(){
        return "лопнуть";
    }
    public String burst(ActionStatus status){
        return status.toString()+" " + "лопнуть";
    }

    public String stopTheExistence(){
        return "прекратить своё существование";
    }

    @Override
    public String toString(){
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof StockCompany)) return false;
        StockCompany that = (StockCompany) o;
        return Objects.equals(getName(), that.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName());
    }
}
