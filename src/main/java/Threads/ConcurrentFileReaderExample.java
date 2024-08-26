package Threads;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class ConcurrentFileReaderExample {

    public static void main(String[] args) {

        String filePath1 = "/Users/soban/test_doc/samplelogs1.txt";
        String filePath2 = "/Users/soban/test_doc/samplelogs2.txt";

        Thread fileThread1 = new Thread(() -> readFile(filePath1));
        Thread fileThread2 = new Thread(() -> readFile(filePath2));

        fileThread1.start();
        fileThread2.start();

        try {
            fileThread1.join();
            fileThread2.join();
        } catch (InterruptedException e){
            e.printStackTrace();
        }
    }

    private static void readFile(String filePath){
        try(BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null){
                Thread.sleep(4000);
                System.out.println(Thread.currentThread().getName() + " : reads line  " + line);
            }
        } catch (IOException e){
            e.printStackTrace();
        } catch (InterruptedException e){
            throw  new RuntimeException(e);
        }
    }
}
