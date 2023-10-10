package ru.darek.homework3;

import java.util.Scanner;

public class MainApp {
    public static void main(String[] args) {
        Scanner scanner=new Scanner(System.in);
        System.out.println("Введите число от 1 до 5");
        int a=scanner.nextInt();
        if (a==1){
            greetings();
        } if (a==2){
            checkSign(1,2,3);
        } if (a==3){
            selectColor();
        } if (a==4){
            compareNumbers();
        } if (a==5){
            addOrSubtractAndPrint(2,7,true);
        }
    }
    public static void greetings(){
        System.out.println(" Hello \n World \n from  \n Java");
    }
    public static void checkSign(int a,int b,int c){
        if (a+b+c>0){
            System.out.println("Сумма положительная");
        } else {
            System.out.println("Сумма отрицательная");
        }
    }
    public static void selectColor(){
        int data=9;
        if (data<10){
            System.out.println("Красный");
        }if (data>=10 && data<=20){
            System.out.println("Желтый");
        }if (data>20){
            System.out.println("Зеленый");
        }
    }
    public static void compareNumbers(){
        int a=2;
        int b=7;
        if(a>=b){
            System.out.println("a >= b");
        }else{
            System.out.println("a < b");
        }
    }
    public static void addOrSubtractAndPrint(int initValue, int delta, boolean increment){
        if (increment == true){
            System.out.println(initValue + delta);
        }else {
            System.out.println(initValue - delta);
        }
    }
}
