package ru.ifmo.se.programming.kastricyn;

public class View {
    private String whichView = "";

    public View setWhichView(String whichView) {
        this.whichView = whichView;
        return this;
    }

    public String getWhichView() {
        return whichView;
    }

    @Override
    public String toString() {
        if(whichView.equals(""))
            return "вид";
        else return whichView + " вид";
    }
}
