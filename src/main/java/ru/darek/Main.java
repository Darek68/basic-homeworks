package ru.darek;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        Fruit fruit=new Fruit(8);
        Apple apple=new Apple(2);
        Orange orange=new Orange(1);

        Box<Fruit> boxf=new Box<>(new ArrayList<>());
        boxf.addIntoBox(fruit);
        boxf.addIntoBox(apple);
        boxf.addIntoBox(orange);

        Box<Apple> boxa=new Box<>(new ArrayList<>());
       // boxa.addIntoBox(fruit);
        boxa.addIntoBox(apple);
       // boxa.addIntoBox(orange);


        Box<Orange> boxo=new Box<>(new ArrayList<>());
       // boxo.addIntoBox(fruit);
       // boxo.addIntoBox(apple);
        boxo.addIntoBox(orange);

        System.out.println(boxf.compareTo(boxa));

        boxo.pourOver(boxf.getArr(),boxo.getArr());
       // boxa.pourOver(boxo.getArr(),boxa.getArr());
       // boxf.pourOver(boxo.getArr(),boxf.getArr());
    }
}
