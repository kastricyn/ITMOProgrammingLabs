package ru.ifmo.se.programming.kastricyn;

public class Stock implements Saleable {
    private int price;
    private String name;
    private StockCompany company;

    public StockCompany getCompany(){return company;}

    @Override
    public void setPrice(int price) {
        this.price = price;
    }

    @Override
    public int getPrice() {
        return price;
    }

    @Override
    public String getName() {
        return name;
    }

    protected void setName(String name) {
        this.name = name;
    }

    public Stock(){}
    public Stock(StockCompany company){this.company= company;}

    @Override
    public String toString(){return name;}


}