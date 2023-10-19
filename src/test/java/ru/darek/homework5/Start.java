package ru.darek.homework5;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.stream.IntStream;

public class Start {
    public static void main(String[] args) {
        int[] arr = getArr(10, 12);
        getSumMore5(arr);
        System.out.println("-------------------");
        fillArr((int) (Math.random() * 10), new int[(int) (Math.random() * 10)]);
        System.out.println("-------------------");
        addForElement((int) (Math.random() * 10), getArr(10, 8));
        System.out.println("-------------------");
        halfArrLarger(getArr(6, 10));
        System.out.println("-------------------");
        sumArr(getArr(2, 10), getArr(6, 15), getArr(5, 20));
        System.out.println("-------------------");
        int[] arr2 = {1, 2, 3, 4, 2, 6, 2};
        balance(arr2); // баллансировка массива
        System.out.println("-------------------");
        int[] arr3 = {0, 2, 3, 4, 7, 34, 4};
        progresArr(arr3);
        int[] arr4 = {0, 2, 3, 4, 7, 34};
        progresArr(arr4);
        System.out.println("-------------------");
        reverseArr(getArr(10, 12));

    }

    public static void reverseArr(int[] arr) {
        System.out.println("Исходный массив: " + Arrays.toString(arr));
        int bufer;
        for (int i = 0, j = arr.length - 1; i < arr.length/2; i++, j--) {
            bufer = arr[i];
            arr[i] = arr[j];
            arr[j] = bufer;
        }
        System.out.println("Конечный массив: " + Arrays.toString(arr));
    }

    public static void progresArr(int[] arr) {
        boolean raising = true;
        for (int i = 0; i < arr.length - 1; i++) {
            if (arr[i] >= arr[i + 1]) {
                System.out.println("Массив " + Arrays.toString(arr) + " не возрастающий!");
                raising = false;
                break;
            }
        }
        if (raising){
            System.out.println("Массив " + Arrays.toString(arr) + " является возрастающим!");
        }
    }

    public static void balance(int[] arr) { //точка в массиве
        System.out.println(Arrays.toString(arr));
        int sum = Arrays.stream(arr).sum();
        int sum1 = 0;
        for (int i = 0; i < arr.length; i++) {
            sum1 += arr[i];
            if (sum1 == (sum - sum1)) System.out.println("Место балансировки после элемента " + i);
        }
    }

    public static void sumArr(int[]... inArr) {  // сумма нескольких массивов
        int length = 0;
        for (int[] arr : inArr) {
            System.out.println(Arrays.toString(arr));
            if (length < arr.length) length = arr.length;
        }
        int[] result = new int[length];
        for (int[] arr : inArr) {
            for (int i = 0; i < arr.length; i++) {
                result[i] += arr[i];
            }
        }
        System.out.println("Сумма " + Arrays.toString(result));
    }

    public static void halfArrLarger(int[] arr) {
        if (arr.length % 2 == 1) {
            System.out.println("Массив должен состоять из четного числа элементов!");
        } else {
            System.out.println("Массив: " + Arrays.toString(arr));
            int firstHalfSum = 0,secondHalfSum = 0;
            for (int i = 0,j= arr.length-1;i < arr.length/2; i++,j--) {
                firstHalfSum+=arr[i];
                secondHalfSum+=arr[j];
            }
            if (firstHalfSum == secondHalfSum) {
                System.out.println("Обе половинки равны");
            }
            if (firstHalfSum > secondHalfSum) {
                System.out.println("Первая половинка больше");
            }
            if (firstHalfSum < secondHalfSum) {
                System.out.println("Вторая половинка больше");
            }
        }
    }


    public static void addForElement(int x, int[] arr) {
        System.out.println("Каждый элемент массива " + Arrays.toString(arr) + " увеличим на " + x);
        for (int i = 0; i < arr.length; i++) {
            arr[i] += x;
        }
        System.out.println("Итоговый    массив:   " + Arrays.toString(arr));
    }

    public static void fillArr(int x, int[] arr) {
        System.out.println("Массив длиной в " + arr.length + " заполним значением " + x);
        for (int i = 0; i < arr.length; i++) {
            arr[i] = x;
        }
        System.out.println("Массив: " + Arrays.toString(arr));
    }

    public static void getSumMore5(int[] arr) {
        int sum = 0;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] > 5) sum += arr[i];
        }
        System.out.println("Массив: " + Arrays.toString(arr));
        System.out.println("Сумма всех элементов кот больше 5 : " + sum);
    }

    public static int[] getArr(int l, int max) {
        int[] arr = new int[l];
        for (int i = 0; i < l; i++) {
            arr[i] = (int) (Math.random() * max);
        }
        return arr;
    }
}
