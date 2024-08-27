package Threads;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.concurrent.Callable;

public class FileReaderTask implements Callable {

    String filePath;

    public FileReaderTask(String filePath){
        this.filePath = filePath;
    }
    @Override
    public Object call() throws Exception {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                Thread.sleep(4000);
                System.out.println(Thread.currentThread().getName() + ": reads line  " + line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return true;
    }
}
