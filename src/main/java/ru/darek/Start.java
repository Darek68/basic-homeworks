package ru.darek;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class Start {
    public static void main(String[] args) {
        //List<Integer> i=new LinkedList<>();
        MyLinkedList<String> myLinkedList = new MyLinkedList<>();
        myLinkedList.addFirst("Первый");
        myLinkedList.addLast("Второй");
        myLinkedList.addLast("Третий");
        myLinkedList.addLast("Четвертый");
        myLinkedList.addLast("Пятый");
        myLinkedList.addLast("Шестой");

        System.out.println("1 " + myLinkedList.toString());
        System.out.println("2 " + myLinkedList.toString(true));
        System.out.println("Размер: " + myLinkedList.getSize());
        System.out.println("getFirst " + myLinkedList.getFirst());
        System.out.println("getLast " + myLinkedList.getLast());
        System.out.println("get(3) " + myLinkedList.get(3));
        System.out.println("get(1) " + myLinkedList.get(1));
        myLinkedList.remove(5);
        System.out.println("Удалили 5й элемент: " + myLinkedList.toString(true));
        myLinkedList.remove(5);
        System.out.println("Удалили 5й элемент: " + myLinkedList.toString(true));
        myLinkedList.remove(1);
        System.out.println("Удалили 1й элемент: " + myLinkedList.toString(true));

        System.out.println("Работа очереди:");
        MyQueue<Integer> myQueue=new MyQueue<>();
        myQueue.offer(4);
        myQueue.offer(44);
        myQueue.offer(444);
        System.out.println(myQueue.poll());
        System.out.println(myQueue.poll());
        System.out.println(myQueue.poll());

        System.out.println("Работа стека:");
        MyStack<Integer> myStack=new MyStack<>();
        myStack.push(4);
        myStack.push(44);
        myStack.push(444);
        System.out.println(myStack.pop());
        System.out.println(myStack.pop());
        System.out.println(myStack.pop());



        int[] arr={1,3,5,8,90,4,8,34,11,0};
        System.out.println("До сортировки: " + Arrays.toString(arr));
        System.out.println("После сортировки: " + Arrays.toString(sort(arr)));
    }

    public static int[] sort(int[] array) {
        int item = array[0];
        boolean change = false;
        for (int i = 0; i < array.length; i++) {
            change = false;
            for (int j = 0; j < array.length - 1; j++) {
                if (array[j] > array[j + 1]) {
                    item = array[j + 1];
                    array[j + 1] = array[j];
                    array[j] = item;
                    change = true;
                }
            }
            if (!change) return array;
        }
        return array;
    }

    public static int[] sort2(int[] array) {
        int item = array[0];
        boolean change = false;
        for (int i = 0; i < array.length; i++) {
            change = false;
            for (int j = 0; j < array.length - 1; j++) {
                if (array[j] > array[j + 1]) {
                    item = array[j + 1];
                    array[j + 1] = array[j];
                    array[j] = item;
                    change = true;
                }
            }
            if (!change) return array;
        }
        return array;
    }
}
