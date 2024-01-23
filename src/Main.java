import lock.CASListLock;

public class Main {
    public static void main(String[] args) {
        CASListLock casListLock = new CASListLock();
        casListLock.test();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
        }
        casListLock.print();
    }
}