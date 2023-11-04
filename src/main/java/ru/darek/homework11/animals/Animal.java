package ru.darek.homework11.animals;

public abstract class Animal {
    protected String name;
    protected int runningSpeed;
    protected final int runningEndurance = 1;
    protected int swimmingSpeed;
    protected int swimmingEndurance;
    protected int endurance;

    public float run(int distance) {
        int enduranceСonsumption = distance * this.runningEndurance;
        if (enduranceСonsumption > this.endurance) {
            System.out.println("у животного " + this.name + " появилось состояние усталости");
            return -1;
        }
        this.endurance -= enduranceСonsumption;
        float time = distance / runningSpeed;
        System.out.println("Животное " + this.name + " пробежало " + distance + "м за " + time + " секунд");
        return time;
    }

    public float swim(int distance) {
        if (this.swimmingSpeed == -1) {
            System.out.println("Животное " + this.name + " не плавает!");
            return -1;
        }
        int enduranceСonsumption = distance * this.swimmingEndurance;
        if (enduranceСonsumption > this.endurance) {
            System.out.println("у животного появилось состояние усталости");
            return -1;
        }
        this.endurance -= enduranceСonsumption;
        float time = distance / swimmingSpeed;
        System.out.println("Животное " + this.name + " проплыло " + distance + "м за " + time + " секунд");
        return time;
    }

    public void info() {
        System.out.println("У животного " + this.name + " осталось " + this.endurance + " выносливости");
    }
}
