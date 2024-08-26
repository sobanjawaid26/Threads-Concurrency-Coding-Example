package Threads;

import java.util.concurrent.atomic.AtomicInteger;

public class WithoutAtomicIntegerExample {

    //private static AtomicInteger atomicCounter = new AtomicInteger(0);

    private static Integer counter = 0;
    public static void main(String[] args) {
        Runnable atomicTask = () -> {
            for (int i = 0; i < 10000; i++){
                counter++;
            }
        };
        Thread thread1 = new Thread(atomicTask);
        Thread thread2 = new Thread(atomicTask);

        thread1.start();
        thread2.start();

        try {
            thread1.join();
            thread2.join();
            System.out.println("Atomic counter : " + counter);
        } catch (InterruptedException e){
            e.printStackTrace();
        }
    }
}
