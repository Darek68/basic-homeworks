package ru.darek;

public class Cat {
    private String name;
    private int appetite;
    private boolean satiated;

    public Cat(String name, int appetite) {
        this.name = name;
        this.appetite = appetite;
    }

    public void eat(Plate plate){
        if (plate.reduceFood(appetite)) {
            satiated=true;
        }
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
