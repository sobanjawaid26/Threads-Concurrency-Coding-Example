package Threads;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadPoolExecutorDemo {
    public static void main(String[] args) {
        ExecutorService executer = Executors.newFixedThreadPool(3);

        for(int i = 1; i <= 100; i++){
            int taskNumber = i;
            executer.execute(() -> {
                System.out.println("Task " + taskNumber + " executed by " + Thread.currentThread().getName());
            });
        }

        executer.shutdown();
    }

}
