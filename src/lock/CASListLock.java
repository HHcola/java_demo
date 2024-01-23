package lock;

import java.util.concurrent.locks.Lock;

/**
 * 对于列表使用SynchronizedList加锁，用的是悲观锁，会有内核切换，线程会出现等待，性能上并不是最优
 * 使用CAS+链表的方式构建一个新的队列，降低锁带来的性能开销
 */
public class CASListLock {

    private MessageList<String> messageList = new MessageList<>();
    public void test() {
        int maxSize = 10;
        for (int i = 0; i < maxSize; i ++) {
            Thread thread = new Thread(new LockRun("lockThread" + i), "lockThread" + i);
            thread.start();
        }
    }


    public void print() {
        int maxSize = 1;
        for (int i = 0; i < maxSize; i ++) {
            Thread thread = new Thread(new LockPrintRun("printThread" + i), "printThread" + i);
            thread.start();
        }
    }

    public class LockRun implements Runnable {

        private final String name;
        LockRun(String name) {
            this.name = name;
        }
        @Override
        public void run() {
            for(int i = 0; i < 10; i ++) {
                Node<String> node = new Node<>();
                node.setD(name + "lock" + i);
                messageList.add(node);
            }
        }
    }

    public class LockPrintRun implements Runnable {

        private final String name;

        LockPrintRun(String name) {
            this.name = name;
        }
        @Override
        public void run() {
            for(int i = 0; i < 100; i ++) {
                messageList.get();
            }
        }
    }
}

