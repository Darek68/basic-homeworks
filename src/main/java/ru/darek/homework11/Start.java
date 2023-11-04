package ru.darek.homework11;

import ru.darek.homework11.animals.Cat;
import ru.darek.homework11.animals.Dog;
import ru.darek.homework11.animals.Horse;

public class Start {
    public static void main(String[] args) {
        Cat cat=new Cat("Васька",1,200);
        Dog dog=new Dog("Шарик",2,1,400);
        Horse horse=new Horse("Сивка",4,2,800);
        cat.run(100);
        cat.swim(5);
        cat.run(120);
        System.out.println("----------------");
        dog.run(100);
        dog.swim(5);
        dog.run(120);
        System.out.println("----------------");
        horse.run(100);
        horse.swim(5);
        horse.run(120);
    }
}
