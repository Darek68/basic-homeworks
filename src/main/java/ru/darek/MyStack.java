package ru.darek;

public class MyStack<T> {
    MyLinkedList<T> myLinkedList;

    public MyStack() {
        this.myLinkedList = new MyLinkedList<>();
    }

    public void push(T data) {
        myLinkedList.addFirst(data);
    }

    public T pop() {
        T res = myLinkedList.getFirst();
        myLinkedList.remove(1);
        return res;
    }
}
