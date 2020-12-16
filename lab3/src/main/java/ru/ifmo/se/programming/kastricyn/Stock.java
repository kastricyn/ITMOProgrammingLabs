package ru.ifmo.se.programming.kastricyn;

import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Stock)) return false;
        Stock stock = (Stock) o;
        return getPrice() == stock.getPrice() && Objects.equals(getName(), stock.getName()) && Objects.equals(getCompany(), stock.getCompany());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getPrice(), getName(), getCompany());
    }
}