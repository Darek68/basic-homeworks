package ru.darek;

public class Node<T> {
    T value;
    Node<T> previous;
    Node<T> next;

    public Node(T date, Node<T> previous, Node<T> next) {
        this.value = date;
        this.previous = previous;
        this.next = next;
    }
}
