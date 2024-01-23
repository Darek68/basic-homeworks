package ru.darek;

import java.util.concurrent.TimeUnit;

public class MyArrLib {


    public static int[] GetArrAfterOne(int[] arr) {
        int n = -1;
        for (int i = arr.length - 1; i >= 0; i--) {
            if (arr[i] == 1) {
                n = i;
                break;
            }
        }
        if (n == -1) {
            throw new RuntimeException();
        }
        int[] result = new int[arr.length - n -1];
        for (int i = 0; i < result.length; i++) {
            result[i] = arr[n + i + 1];
        }
        try {
            TimeUnit.MICROSECONDS.sleep(2);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return result;
    }
}
