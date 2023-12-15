package ru.darek;

public class MyMain {
    public static void main(String[] args) throws InterruptedException {
        int lenArr = 100000000;
        int cntCores = 4;
        double[] arr1 = new double[lenArr];
        int start = 0;
        int stop = 0;

        Long time = System.currentTimeMillis();

        for (int i = 0; i < arr1.length; i++) {
            arr1[i] = 1.14 * Math.cos(i) * Math.sin(i * 0.2) * Math.cos(i / 1.2);
        }
        System.out.println("Время работы 1 потока: " + (System.currentTimeMillis() - time) + "мс");
        System.out.println(arr1[lenArr - 3] + "\n");

        // хардкорный вариант
        double[] arr2 = new double[lenArr];
        Thread t1 = new Thread(() -> {
            for (int j = 0; j < 25000000; j++) {
                arr2[j] = 1.14 * Math.cos(j) * Math.sin(j * 0.2) * Math.cos(j / 1.2);
            }
        });
        Thread t2 = new Thread(() -> {
            for (int i = 25000000; i < 50000000; i++) {
                arr2[i] = 1.14 * Math.cos(i) * Math.sin(i * 0.2) * Math.cos(i / 1.2);
            }
        });
        Thread t3 = new Thread(() -> {
            for (int i = 50000000; i < 75000000; i++) {
                arr2[i] = 1.14 * Math.cos(i) * Math.sin(i * 0.2) * Math.cos(i / 1.2);
            }
        });
        Thread t4 = new Thread(() -> {
            for (int i = 75000000; i < 100000000; i++) {
                arr2[i] = 1.14 * Math.cos(i) * Math.sin(i * 0.2) * Math.cos(i / 1.2);
            }
        });

        time = System.currentTimeMillis();
        t1.start();
        t2.start();
        t3.start();
        t4.start();
        t1.join();
        t2.join();
        t3.join();
        t4.join();

        System.out.println("Время работы " + cntCores + " хардкорных потоков: " + (System.currentTimeMillis() - time) + "мс");
        System.out.println(arr2[lenArr - 3] + "\n");

// Генерация с вызовом класса MyTask-хардкорные парамметры
        double[] arr3 = new double[lenArr];
        time = System.currentTimeMillis();
        Thread tt1 = new Thread(new MyTask(0, 25000000, arr3));
        Thread tt2 = new Thread(new MyTask(25000000, 50000000, arr3));
        Thread tt3 = new Thread(new MyTask(50000000, 75000000, arr3));
        Thread tt4 = new Thread(new MyTask(75000000, 100000000, arr3));
        tt1.start();
        tt2.start();
        tt3.start();
        tt4.start();
        tt1.join();
        tt2.join();
        tt3.join();
        tt4.join();
        System.out.println("Время работы " + cntCores + " сгенерированных потоков(MyTask-хардкор): " + (System.currentTimeMillis() - time) + "мс");
        System.out.println(arr3[lenArr - 3] + "\n");

  //     Генерация с вызовом класса MyTask - динамические параметры
        time = System.currentTimeMillis();
        double[] arr4 = new double[lenArr];
        Thread[] arrThred = new Thread[cntCores];
        for (int i = 0; i < cntCores; i++) {
            start = stop;
            stop = (lenArr * (i + 1) / cntCores);
            arrThred[i] = new Thread(new MyTask(start, stop, arr4));
            arrThred[i].start();
            arrThred[i].join();
        }
        System.out.println("Время работы " + cntCores + " сгенерированных потоков(MyTask-динамика): " + (System.currentTimeMillis() - time) + "мс");
        System.out.println(arr4[lenArr - 3] + "\n");


// Генерация с лямбдой
        double[] arr5 = new double[lenArr];
        int[][] border = new int[cntCores][2];
        Long finalTime = time;
        Thread[] arrThredx = new Thread[cntCores];

        for (int i = 0; i < cntCores; i++) {
            start = stop;
            stop = (lenArr * (i + 1) / cntCores);
            border[i][0] = start;
            border[i][1] = stop;
            int finalI = i;
            arrThredx[i] = new Thread(() -> {
                System.out.println("Старт  потока " + arrThred[finalI].getName() + " " + border[finalI][0] + "-" + border[finalI][1]);
                for (int j = border[finalI][0]; j < border[finalI][1]; j++) {
                    arr5[j] = 1.14 * Math.cos(j) * Math.sin(j * 0.2) * Math.cos(j / 1.2);
                }
            });
        }
        time = System.currentTimeMillis();
        for (int i = 0; i < cntCores; i++) {
            arrThredx[i].start();
            arrThredx[i].join();
        }
        System.out.println("Время работы " + cntCores + "  потоков сгенерированных лямбдой: " + (System.currentTimeMillis() - time) + "мс");
        System.out.println(arr5[lenArr - 3] + "\n");

    }
}

/*
Реализация №1

Реализуйте метод, который создает double массив длиной 100_000_000 элементов
Метод должен должен циклом for пройти по каждому элементу и посчитать его значение по формуле: array[i] = 1.14 * Math.cos(i) * Math.sin(i * 0.2) * Math.cos(i / 1.2);
Засеките время выполнения цикла и выведите его в консоль.
Реализация №2:

Сделайте то же самое что и в реализации один, только чтобы массив заполняли 4 потока одновременно.
То есть первый поток заполняет первую четверть массива, второй - вторую и т.д.
И посмотрите насколько быстрее выполнится работа по сравнению с одним потоком.
 */