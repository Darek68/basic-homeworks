package ru.darek;

import java.util.Arrays;

public class Start {
    public static void main(String[] args) {
        int sumArr = 0;
        String[][] arr0 = {{"3", "2", "8", "35"}, {"3", "2", "8", "35"}, {"3", "2", "8", "35"}};
        String[][] arr1 = {{"3", "2", "8", "35"}, {"3", "2", "8", "35"}, {"3", "2"},{"3", "2", "8", "35"}};
        String[][] arr2 = {{"3", "2", "8", "35"}, {"3", "2", "8", "35"}, {"3", "i", "8", "35"}, {"33", "2", "81", "5"}};
        String[][] arr3 = {{"3", "2", "8", "35"}, {"3", "2", "8", "35"}, {"3", "2", "8", "35"}, {"33", "2", "81", "5"}};
        String[][][] arrs={arr0,arr1,arr2,arr3};
        String[][] arr;

        for (int i = 0; i < 4; i++) {
            arr=arrs[i];
            System.out.println("\n Суммируем массив:\n" + Arrays.deepToString(arr).replace("], ", "]\n"));
            try {
                sumArr = getSumArr(arr);
                System.out.println("Сумма элементов массива ровна " + sumArr);
            } catch (AppArraySizeException e) {
                System.out.println("Передан массив неправильного размера: " + e);
            } catch (AppArrayDataException e) {
                System.out.println("В переданном массиве оказались ошибочные данные: " + e);
            }
        }
    }

    public static int getSumArr(String[][] arr) {
        if (arr.length != 4)
            throw new AppArraySizeException("Первый размер входного массива равен " + arr.length + " а требовалось 4");
        int sum = 0;
        int cell = 0;
        for (int i = 0; i < 4; i++) {
            if (arr[i].length != 4)
                throw new AppArraySizeException("Размер строки " + (i+1) + " равен " + arr[i].length + " а требовалось 4");
            for (int j = 0; j < 4; j++) {
                try {
                    sum += Integer.parseInt(arr[i][j]);
                } catch (NumberFormatException e) {
                    throw new AppArrayDataException("Значение ячейки массива не возможно преобразовать в число!", i, j);
                }
                sum += cell;
            }
        }
        return sum;
    }
}
