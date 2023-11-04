package ru.darek.homework11.animals;

public class Dog extends Animal {
    public Dog(String name, int runningSpeed, int swimmingSpeed, int endurance) {
        this.name = name;
        this.runningSpeed = runningSpeed;
        this.swimmingSpeed = swimmingSpeed;
        this.swimmingEndurance = 2;
        this.endurance = endurance;
    }
}
