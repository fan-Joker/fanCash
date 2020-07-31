package me.fanjoker.cash.others;

public class CashPlayer {

    private String name;
    private double value;
    private boolean toggle;

    public CashPlayer(String name, double value, boolean toggle) {
        this.name = name;
        this.value = value;
        this.toggle = toggle;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public boolean isToggle() {
        return toggle;
    }

    public void setToggle(boolean toggle) {
        this.toggle = toggle;
    }
}
