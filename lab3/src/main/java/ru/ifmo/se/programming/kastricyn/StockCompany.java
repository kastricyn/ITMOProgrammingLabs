package ru.ifmo.se.programming.kastricyn;

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

}
