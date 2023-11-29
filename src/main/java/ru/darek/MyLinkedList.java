package ru.darek;

public class MyLinkedList<T> {
    private Node<T> first;
    private Node<T> last;
    private int size;

    public MyLinkedList() {
    }

    public void addFirst(T date) {
        Node<T> newNode = new Node<>(date, null, first);
        if (size == 0) last = newNode;
        if (first != null) first.previous = newNode;
        first = newNode;
        size++;
    }

    public void addLast(T date) {
        Node<T> newNode = new Node<>(date, last, null);
        if (size == 0) first = newNode;
        if (last != null) last.next = newNode;
        last = newNode;
        size++;
    }

    public T getFirst() {
        if (first != null) return first.value;
        return null;
    }

    public T getLast() {
        if (last != null) return last.value;
        return null;
    }

    public void add(int position, T data) {
        Node<T> nodeTmp = first;
        for (int i = 0; i <= position; i++) {
            nodeTmp = nodeTmp.next;
            if (i == position) nodeTmp.value = data;
            return;
        }
    }

    public void remove(int position) {
        if (position<1||position>size) return;
        Node<T> nodeTmp = first;
        for (int i = 0; i < position; i++) {
            if (i == (position - 1)) {
                if (nodeTmp.previous!=null)nodeTmp.previous.next = nodeTmp.next;
                if (nodeTmp.next!=null)nodeTmp.next.previous = nodeTmp.previous;
                if (position==1)first=first.next;
                if (position==size)last=last.previous;
                size--;
                return;
            }
            nodeTmp = nodeTmp.next;
        }
    }

    public T get(int position) {
        Node<T> nodeTmp = first;
        for (int i = 0; i < position; i++) {
            if ((i + 1) == position) {
                return nodeTmp.value;
            }
            nodeTmp = nodeTmp.next;
        }
        return null;
    }

    public int getSize() {
        return size;
    }
    @Override
    public String toString() {
        return "MyLinkedList{" + "first=" + first.value + ", last=" + last.value + ", size=" + size + '}';
    }
    public String toString(boolean all) {
        if (!all) return toString();
        Node<T> nodeTmp = first;
        String res = "MyLinkedList{";
        for (int i = 0; i < size-1; i++) {
            res += (nodeTmp.value.toString() + ",");
            nodeTmp = nodeTmp.next;
        }
        res+=last.value.toString() + "}\n";
        return res;
    }
}
