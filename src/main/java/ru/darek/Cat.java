package ru.darek;

public class Cat {
    private String name;
    private int appetite;

    public int getAppetite() {
        return appetite;
    }

    private boolean satiated = false;

    public Cat(String name, int appetite) {
        this.name = name;
        this.appetite = appetite;
    }

    public void setSatiated(boolean satiated) {
        this.satiated = satiated;
    }

    @Override
    public String toString() {
        if (this.satiated) {
            return "Кот " + this.name + " сытый!";
        } else {
            return "Кот " + this.name + " голодный!";
        }

    }
}
