package lock;

import java.util.concurrent.atomic.AtomicReference;

public class MessageList<T> {
    Node<T> first;
    Node<T> end;

    private Node<T> nextNode = new Node<>();
    MessageList() {
        first = new Node<>();
        first.setNext(new AtomicReference<>(nextNode));
        end = first;
    }

    public void add(Node<T> data) {
        System.out.println("add node:" + data.getD() + " Thead Name:" + Thread.currentThread().getName());
        do {
            data.setSize(end.getSize() + 1);
            data.setNext(new AtomicReference<>(nextNode));
        } while (!end.getNext().compareAndSet(nextNode, data));
         end = data;
         System.out.println("add node success:" + data.getSize() + " msg " +  data.getD() + " Thead Name:" + Thread.currentThread().getName());
    }

    public Node<T> get() {
        if (first.getNext().get().getD() != null) {
            first = first.getNext().get();
            System.out.println("get node success:" + first.getD() + " Thead Name:" + Thread.currentThread().getName());
            return first;
        }
        return null;
    }
}
