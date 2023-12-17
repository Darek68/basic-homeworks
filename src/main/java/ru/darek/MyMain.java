package ru.darek;

public class MyMain {
    final static int LEN_ARR = 100000000;
    public static void main(String[] args) throws InterruptedException {       

        double[] arr1 = new double[LEN_ARR];
        Long time = System.currentTimeMillis();

        for (int i = 0; i < arr1.length; i++) {
            arr1[i] = 1.14 * Math.cos(i) * Math.sin(i * 0.2) * Math.cos(i / 1.2);
        }
        System.out.println("Время работы 1 потока: " + (System.currentTimeMillis() - time) + "мс");

        for (int i = 2; i < 10; i++) {
            runMultiStream(i);
        }
    }
    public static void runMultiStream(int cntCores) throws InterruptedException {
        Long time = System.currentTimeMillis();
        int start = 0;
        int stop = 0;
// Генерация с лямбдой
        double[] arr5 = new double[LEN_ARR];
        int[][] border = new int[cntCores][2];
        Thread[] arrThredx = new Thread[cntCores];

        for (int i = 0; i < cntCores; i++) {
            start = stop;
            stop = (LEN_ARR * (i + 1) / cntCores);
            border[i][0] = start;
            border[i][1] = stop;
            int finalI = i;
            arrThredx[i] = new Thread(() -> {
                for (int j = border[finalI][0]; j < border[finalI][1]; j++) {
                    arr5[j] = 1.14 * Math.cos(j) * Math.sin(j * 0.2) * Math.cos(j / 1.2);
                }
            });
        }
        time = System.currentTimeMillis();
        for (int i = 0; i < cntCores; i++) {arrThredx[i].start();}
        for (int i = 0; i < cntCores; i++) {arrThredx[i].join();}
        System.out.println("Время работы " + cntCores + " потоков: " + (System.currentTimeMillis() - time) + "мс");
    }
}
