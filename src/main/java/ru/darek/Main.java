package ru.darek;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
    public static String letter = "C";
    public static Object mon = new Object();
    public static String[] arr = {"C", "A", "B", "C"};

    public static void main(String[] args) {

        ExecutorService executorService = Executors.newFixedThreadPool(3);
        for (int i = 0; i < 3; i++) {
            int finalI = i;
            int finalI2 = i + 1;
            executorService.execute(() -> {
                int n=0;
                while (n<5) {
                    n++;
                    synchronized (mon){
                        while (letter!=arr[finalI]){
                            try {
                                mon.wait();
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                        letter = arr[finalI2];
                        System.out.print(letter);
                        mon.notifyAll();
                        try {
                            Thread.sleep(500);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            });
        }
        executorService.shutdown();
    }
}
