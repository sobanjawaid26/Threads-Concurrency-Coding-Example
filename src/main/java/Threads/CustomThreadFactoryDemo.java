package Threads;

import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/*
 *
 * This program demonstrates the usage of ThreadPoolExecutor's various configuration parameters, including core pool size, maximum pool size, and keep-alive time.
 * It uses a custom thread factory to create threads with a specific naming convention.
 *
 * You are tasked with designing a system that efficiently manages the execution of tasks using a thread pool.
 *
 *  The goal is to demonstrate the use of a custom thread factory to create threads with specific properties and
 *  the configuration of a ThreadPoolExecutor.
 *
 */
public class CustomThreadFactoryDemo {
    public static void main(String[] args) {

        ThreadFactory threadFactory = new CustomThreadFactory();

        ThreadPoolExecutor executor = new ThreadPoolExecutor(
                3,
                5,
                30,
                TimeUnit.SECONDS,
                new LinkedBlockingDeque<>(),
                threadFactory
        );

        for (int i = 1; i <= 10; i++){
            final int taskId = i;
            executor.submit(() -> {
                System.out.println("Task " + taskId + " executed by thread: " + Thread.currentThread().getName());
            });
        }
        executor.shutdown();
    }
    static class CustomThreadFactory implements ThreadFactory {
        private static int threadCount = 1;

        @Override
        public  Thread newThread(Runnable r){
            Thread thread = new Thread(r,"MyCustomThread-" + threadCount++);
            return thread;
        }
    }

}


