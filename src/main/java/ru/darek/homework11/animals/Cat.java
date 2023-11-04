package ru.darek.homework11.animals;

public class Cat extends Animal {
    public Cat(String name, int runningSpeed, int endurance) {
        this.name = name;
        this.runningSpeed = runningSpeed;
        this.swimmingSpeed = -1;
        this.swimmingEndurance = -1;
        this.endurance = endurance;
    }
}
