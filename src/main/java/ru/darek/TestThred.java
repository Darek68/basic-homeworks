package ru.darek;


class MyThread extends Thread {
double[] arr;
int start;
int stop;
    public MyThread (String s,double[] arr,int start,int stop) {
        super(s);
        this.arr = arr;
        this.start=start;
        this.stop=stop;
    }

    public void run() {
        System.out.println("Run: "+ getName() + " " + start + " " + stop);
        for (int i = start; i < stop; i++) {
            arr[i] = 1.14 * Math.cos(i) * Math.sin(i * 0.2) * Math.cos(i / 1.2);
        }
    }
}


class TestThread {
    public static void main (String arg[]) throws InterruptedException {
        int lenArr = 100000000;
        int cntCores = 4;
        double[] arr1 = new double[lenArr];
        int start = 0;
        int stop = 0;
        Thread[] arrThred = new Thread[cntCores];

        for (int x=0; x<cntCores; x++)
        {
            start = stop;
            stop = (lenArr * (x + 1) / cntCores);
            arrThred[x]= new MyThread("Thread #" + x,arr1,start,stop);
            arrThred[x].start();
            arrThred[x].join();
            System.out.println("Started Thread:" + x);
        }
    }
}

