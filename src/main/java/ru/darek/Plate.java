package ru.darek;

public class Plate {
    private int foodMax;
    private int foodCurrent;

    public Plate(int foodMax) {
        this.foodMax = foodMax;
        this.foodCurrent = this.foodMax;
    }

    public void addFood(int amount) {
        this.foodCurrent += amount;
        if (this.foodCurrent > this.foodMax) this.foodCurrent = this.foodMax;
    }

    public boolean reduceFood(int amount) {
        if (amount > this.foodCurrent) return false;
        this.foodCurrent -= amount;
        return true;
    }
}

