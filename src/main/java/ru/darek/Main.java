package ru.darek;

import java.sql.Array;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        int[] arr1 =  { 5, 4, 1, 20 ,30 };
        int[] arr2 =  { 1, 2, 3, 4 ,40 };
        int[] arr3 =  { 21, 333, 5, 1, 1, 9, 900 };
        System.out.println(Arrays.toString(MyArrLib.GetArrAfterOne(arr1)));
        System.out.println(Arrays.toString(MyArrLib.GetArrAfterOne(arr2)));
        System.out.println(Arrays.toString(MyArrLib.GetArrAfterOne(arr3)));
    }

}
