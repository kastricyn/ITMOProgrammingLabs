package ru.ifmo.se.programming.kastricyn;

import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof View)) return false;
        View view = (View) o;
        return Objects.equals(getWhichView(), view.getWhichView());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getWhichView());
    }
}
