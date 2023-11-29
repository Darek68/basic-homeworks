package ru.darek;

public class MyQueue<T> {
    MyLinkedList<T> myLinkedList;

    public MyQueue() {
        this.myLinkedList = new MyLinkedList<>();
    }
    public void offer(T data){
        myLinkedList.addLast(data);
    }
    public T  poll(){
        T res = myLinkedList.getFirst();
        myLinkedList.remove(1);
        return res;
    }
}
