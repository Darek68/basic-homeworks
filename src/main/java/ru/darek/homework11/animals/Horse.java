package ru.darek.homework11.animals;

public class Horse extends Animal {
    public Horse(String name, int runningSpeed, int swimmingSpeed, int endurance) {
        this.name = name;
        this.runningSpeed = runningSpeed;
        this.swimmingSpeed = swimmingSpeed;
        this.swimmingEndurance = 4;
        this.endurance = endurance;
    }
}
