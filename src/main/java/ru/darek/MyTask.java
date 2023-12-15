package ru.darek;

public class MyTask implements Runnable{
    private int start;
    private int  stop;
    private double[] arr;

    public MyTask(int start, int stop, double[] arr){
        this.start = start;
        this.stop = stop;
        this.arr = arr;
    }
    @Override
    public void run(){
        //System.out.println(Thread.currentThread().getName() + " " + start + " - " + stop);
        for (int i = start; i < stop; i++) {
            arr[i] = 1.14 * Math.cos(i) * Math.sin(i * 0.2) * Math.cos(i / 1.2);
        }
    }
}
