package lock;

import java.util.concurrent.atomic.AtomicReference;

public class Node<T> {
    private AtomicReference<Node<T>> next = null;
    private T d;
    private int size;

    public AtomicReference<Node<T>> getNext() {
        return next;
    }

    public void setNext(AtomicReference<Node<T>> next) {
        this.next = next;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public T getD() {
        return d;
    }

    public void setD(T d) {
        this.d = d;
    }
}
