package ru.darek.homework10;

import com.sun.source.tree.IfTree;

public class Box {
    private final int length;
    private final int width;
    private final int height;
    String color;
    private boolean isOpen;
    private String item;

    public Box(int length, int width, int height, String color) {
        this.length = length;
        this.width = width;
        this.height = height;
        this.color = color;
        //this.isEmpty = true;
        this.isOpen = false;
        this.item = null;
    }

    @Override
    public String toString() {
        String res = "Коробка{длиной: " + length + " шириной: " + width + " высотой: " + height +
                "\n цвета: " + color + "\n";
        if (this.item == null) {
            res += " пустая и ";
        } else {
            res += " заполенна предметом " + this.item + " и ";
        }
        if (isOpen) {
            res += " открыта ";
        } else {
            res += " закрыта ";
        }
        return res;
    }

    public void open() {
        if (this.isOpen) {
            System.out.println("Коробка и так открыта!");
            return;
        }
        this.isOpen = true;
        System.out.println("Мы открыли коробку");
    }

    public void close() {
        if (!this.isOpen) {
            System.out.println("Коробка и так закрыта!");
            return;
        }
        this.isOpen = false;
        System.out.println("Мы закрыли коробку");
    }

    public void putItem(String item) {
        if (!this.isOpen) {
            System.out.println("Нельзя положить в закрыту коробку!");
        } else if (this.item != null) {
            System.out.println("Нельзя положить в заполненую коробку!");
        } else {
            this.item = item;
            System.out.println("В коробку положили " + this.item);
        }
    }

    public String getItem() {
        if (!this.isOpen) {
            System.out.println("Нельзя достать предмет из закрытой коробки!");
            return null;
        }
        if (this.item == null) {
            System.out.println("Коробка пуста!");
            return null;
        }
        System.out.println("Из коробки достали " + this.item);
        String res = this.item;
        this.item = null;
        return res;
    }
}
