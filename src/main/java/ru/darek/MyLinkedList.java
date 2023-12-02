package ru.darek;

public class MyLinkedList<T> {
    private Node<T> first;
    private Node<T> last;
    private int size;

    /**
     * Конструктор - создание нового линкедлиста
     */
    public MyLinkedList() {
        size = 0;
    }

    /**
     * Метод добавления элемента в начало листа
     * @return void
     */
    public void addFirst(T date) {
        Node<T> newNode = new Node<>(date, null, first);
        if (size == 0) last = newNode;
        if (first != null) first.previous = newNode;
        first = newNode;
        size++;
    }

    /**
     * Метод добавления элемента в конец листа
     * @return void
     */
    public void addLast(T date) {
        Node<T> newNode = new Node<>(date, last, null);
        if (size == 0) first = newNode;
        if (last != null) last.next = newNode;
        last = newNode;
        size++;
    }

    /**
     * Метод получения первого элемента листа
     * @return возвращает значение из первой ноды
     */
    public T getFirst() {
        if (first != null) return first.value;
        return null;
    }

    /**
     * Метод получения последнего элемента листа
     * @return возвращает значение из последней ноды
     */
    public T getLast() {
        if (last != null) return last.value;
        return null;
    }

    /**
     * Метод записи нового значения в ноду position
     * @param position - номер ноды в листе (номерация начинается с 1!)
     * @param data     - значение записываемое в ноду
     * @return void
     */
    public void add(int position, T data) {
        if (position > size) throw new RuntimeException("Попытка записать в несуществующий элемент!");
        Node<T> nodeTmp = first;
        for (int i = 0; i <= position; i++) {
            nodeTmp = nodeTmp.next;
            if (i == position) nodeTmp.value = data;
            return;
        }
    }

    /**
     * Метод удаления ноды под номером position
     * @param position - номер ноды в листе (номерация начинается с 1!)
     * @return void
     */
    public void remove(int position) {
        if (position < 1 || position > size) return;
        Node<T> nodeTmp = first;
        for (int i = 0; i < position; i++) {
            if (i == (position - 1)) {
                if (nodeTmp.previous != null) nodeTmp.previous.next = nodeTmp.next;
                if (nodeTmp.next != null) nodeTmp.next.previous = nodeTmp.previous;
                if (position == 1) first = first.next;
                if (position == size) last = last.previous;
                size--;
                return;
            }
            nodeTmp = nodeTmp.next;
        }
    }

    /**
     * Метод получения значения из ноды под номером position
     * @param position - номер ноды в листе (номерация начинается с 1!)
     * @return возвращает значение из ноды под номером position
     */
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

    /**
     * Метод получения размера листа
     * @return возвращает размер листа
     */
    public int getSize() {
        return size;
    }

    /**
     * Метод получения строки из листа
     * @return возвращает строку полученную на основе листа (значение первого, последнего элементов и размер)
     */
    @Override
    public String toString() {
        return "MyLinkedList{" + "first=" + first.value + ", last=" + last.value + ", size=" + size + '}';
    }

    /**
     * Метод получения строки из листа
     * @param all - признак выгрузки в строку всех элементов листа
     * @return возвращает строку полученную на основе значений всех нод листа
     */
    public String toString(boolean all) {
        if (!all) return toString();
        Node<T> nodeTmp = first;
        String res = "MyLinkedList{";
        for (int i = 0; i < size - 1; i++) {
            res += (nodeTmp.value.toString() + ",");
            nodeTmp = nodeTmp.next;
        }
        res += last.value.toString() + "}\n";
        return res;
    }
}

class Node<T> {
    T value;
    Node<T> previous;
    Node<T> next;

    public Node(T data, Node<T> previous, Node<T> next) {
        this.value = data;
        this.previous = previous;
        this.next = next;
    }
}

