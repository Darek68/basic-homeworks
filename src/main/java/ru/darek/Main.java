package ru.darek;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        Fruit fruit=new Fruit(8);
        Apple apple=new Apple(2);
        Orange orange=new Orange(1);

        Box<Fruit> boxf=new Box<>(new ArrayList<>());
        boxf.add(fruit);
        boxf.add(apple);
        boxf.add(orange);

        Box<Apple> boxa=new Box<>(new ArrayList<>());
       // boxa.add(fruit);
        boxa.add(apple);
       // boxa.add(orange);


        Box<Orange> boxo=new Box<>(new ArrayList<>());
       // boxo.add(fruit);
       // boxo.add(apple);
        boxo.add(orange);

        System.out.println(boxf.compareTo(boxa));

        boxo.pourOver(boxf/*,boxo.getArr()*/);
       // boxa.pourOver(boxo.getArr(),boxa.getArr());
       // boxf.pourOver(boxo.getArr(),boxf.getArr());
    }
}
