package ru.darek;

public class Start {
    public static void main(String[] args) {
        Cat[] cats = {new Cat("Васька", 11), new Cat("Бурка>", 8), new Cat("Черныш", 15), new Cat("Милок", 5)};
        Plate plate = new Plate(30);

        for (int i = 0; i < cats.length; i++) {
           cats[i].eat(plate);
        }

        for (int i = 0; i < cats.length; i++) {
            System.out.println(cats[i].toString());
        }
    }
}
