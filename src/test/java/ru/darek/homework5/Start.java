package ru.darek.homework5;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.stream.IntStream;

public class Start {
    public static void main(String[] args) {
        int[] arr=getArr(10,12);
        getSumMore5(arr);
        System.out.println("-------------------");
        fillArr((int)(Math.random() * 10),new int[(int)(Math.random() * 10)]);
        System.out.println("-------------------");
        addForElement((int)(Math.random() * 10),getArr(10,8));
        System.out.println("-------------------");
        halfArrLarger(getArr(6,10));
        System.out.println("-------------------");
        System.out.println("-------------------");

    }

    public static void halfArrLarger(int[] arr){
        if (arr.length%2==1){
            System.out.println("Массив должен состоять из четного числа элементов!");
        } else {
            System.out.println("Массив: " + Arrays.toString(arr));
            int sum = Arrays.stream(arr).sum(); // общая сумма
            int[] arr1 = Arrays.copyOf(arr,arr.length/2); // первая половинка
            int sum1 = Arrays.stream(arr1).sum(); // сумма первой половинки
            int sum2 = sum - sum1;
            if (sum1==sum2) { System.out.println("Обе половинки равны"); }
            if (sum1>sum2) { System.out.println("Первая половинка больше"); }
            if (sum1<sum2) { System.out.println("Вторая половинка больше"); }
        }
    }


    public static void addForElement(int x,int[] arr){
        System.out.println("Каждый элемент массива " + Arrays.toString(arr) + " увеличим на " + x);
        for (int i=0;i<arr.length;i++){
            arr[i] += x;
        }
        System.out.println("Итоговый    массив:   " + Arrays.toString(arr));
    }
    public static void fillArr(int x,int[] arr){
        System.out.println("Массив длиной в " + arr.length + " заполним значением " + x);
        for (int i=0;i<arr.length;i++){
            arr[i] = x;
        }
        System.out.println("Массив: " + Arrays.toString(arr));
    }
    public static void getSumMore5(int[] arr){
        int sum=0;
        for (int i=0;i<arr.length;i++){
            if (arr[i]>5) sum+=arr[i];
        }
        System.out.println("Массив: " + Arrays.toString(arr));
        System.out.println("Сумма всех элементов кот больше 5 : " + sum);
    }
    public static int[] getArr(int l,int max){
        int[] arr=new int[l];
        for (int i = 0; i<l; i++){
            arr[i] = (int)(Math.random() * max);
        }
        return arr;
    }
}
