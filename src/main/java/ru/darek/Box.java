package ru.darek;

import java.util.ArrayList;
import java.util.List;

public class Box <T extends Fruit> {
    private List<T> arr;

    public Box(ArrayList<T> arr) {
        this.arr = arr;
    }
    public void add(T element){
        arr.add(element);
    }
    public int getWeight(){
        int result=0;
        for(int i =0; i <arr.size(); i++){
            result += arr.get(i).getWeight();
        }
        return result;
    }

    @Override
    public String toString() {
        return "Box{" +
                "arr=" + arr +
                '}';
    }

    public boolean compareTo(Box<? extends Fruit> box) {
        return this.getWeight() == box.getWeight();
    }

    public void pourOver(Box<? super T> destinationBox){
        for(int i =0; i < this.arr.size(); i++){
            destinationBox.arr.add(this.arr.get(i));
        }
        this.arr.clear();
    }
}

/*
Описание/Пошаговая инструкция выполнения домашнего задания:
    Создайте классы Fruit, Apple extends Fruit, Orange extends Fruit;
    Создайте класс Box, в который можно складывать фрукты. Коробки условно сортируются по типу фрукта: Box только для яблок,
    Box только для апельсин, Box и для тех, и для других фруктов. Для хранения фруктов внутри коробки используйте ArrayList;
    Реализуйте метод добавления фрукта в коробку;
    Реализуйте метод weight, который высчитывает вес коробки (например, из веса одного фрукта и их количества, или может через суммирование,
    как посчитаете нужным). Вес фрукта задаете самостоятельно, единицы измерения не важны;
    Реализуйте метод compare, позволяющий сравнить текущую коробку с переданной в качестве аргумента. true – если их массы равны.
    Можно сравнивать коробки с разными типами фруктов;
    Реализуйте метод, позволяющий пересыпать фрукты из текущей коробки в другую. Помним про сортировку фруктов в коробках;
 */