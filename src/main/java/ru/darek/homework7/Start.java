package ru.darek.homework7;


import java.util.Arrays;

public class Start {
    public static void main(String[] args) {
        int[][] arr = {{2, 0, 8}, {1, 14, -33}};
        // сумма всех положительных
        System.out.println(sumOfPositiveElements(arr));
        System.out.println("------------------------------");

        squareBySize(11); // кадрат *
        System.out.println("------------------------------");

        int[][] bigArr = new int[9][9];
        bigArr = fillRandomArr(bigArr, 9); //заполнить случайными
        diagonal0(bigArr); //обнулить диагональ
        viewArr(bigArr);
        System.out.println("---------------------------------");

        bigArr = fillRandomArr(bigArr, 200); //заполнить случайными
        System.out.println("максимальный элемент массива.. ");
        viewArr(bigArr);
        System.out.println("равен " + findMax(bigArr));
        System.out.println("---------------------------------");

        System.out.println("Массив: ");
        arr=fillRandomArr(arr,9);
        viewArr(arr);
        System.out.println("Сумма второй строки: " + sumInSecondLine(arr));
        int[][] arrOneLine = {{2, 0, 8}};
        System.out.println("Массив: ");
        viewArr(arrOneLine);
        System.out.println("Сумма второй строки: " + sumInSecondLine(arrOneLine));

    }

    public static int sumInSecondLine(int[][] arr) {
        if (arr.length < 2) {
            return -1;
        }
        int sum = 0;
        for (int j = 0; j < arr[1].length; j++) {
            sum += arr[1][j];
        }
        return sum;
    }

    public static int findMax(int[][] array) {
        int max = array[0][0];
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[0].length; j++) {
                if (array[i][j] > max) {
                    max = array[i][j];
                }
            }
        }
        return max;
    }

    public static void diagonal0(int[][] arr) {
        for (int i = 0; i < arr.length; i++) {
                    arr[i][i] = 0;
                    arr[i][arr[0].length - i - 1] = 0;
        }
    }

    public static void squareBySize(int size) { // кадрат *
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (i == 0 || i == size - 1 || j==0 || j== size-1) {
                    System.out.print("*");
                } else {
                    System.out.print(" ");
                }
            }
            System.out.println("");
        }
    }

    public static int sumOfPositiveElements(int[][] arr) { // сумма всех положительных
        int sumArr = 0;
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[0].length; j++) {
                if (arr[i][j] > 0) {
                    sumArr += arr[i][j];
                }
            }
        }
        return sumArr;
    }

    public static int[][] fillRandomArr(int[][] arr, int size) { //заполнить двухмерный случайными
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[0].length; j++) {
                arr[i][j] = (int) (Math.random() * size) + 1;
            }
        }
        return arr;
    }

    public static void viewArr(int[][] arr) {
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[0].length; j++) {
                System.out.print(arr[i][j] + " ");
            }
            System.out.println();
        }
    }
}
