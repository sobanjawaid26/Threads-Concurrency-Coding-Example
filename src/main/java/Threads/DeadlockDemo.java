package Threads;

public class DeadlockDemo {

    public static void main(String[] args) {

        Object varshaKey = new Object();
        Object harshaKey = new Object();

        Thread varsha = new Thread(() -> {
            synchronized (harshaKey){
                System.out.println("Varsha has acquired Harsha's Key");
                try {
                    System.out.println("Varsha Sleeping for 3 secs");
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                System.out.println("Varsha Woke up");
                synchronized (varshaKey){
                    System.out.println("Varsha has got her key");
                }
            }
        });

        Thread harsha = new Thread(() -> {
            synchronized (harshaKey) {
                System.out.println("Harsha has acquired Varsha's Key");
                try{
                    System.out.println("Harsha sleeping fr 3 secs");
                    Thread.sleep(3000);
                } catch (InterruptedException e){
                    throw new RuntimeException(e);
                }
                System.out.println("Harsha woke up");

                synchronized (varshaKey){
                    System.out.println("Varsha has got her key");
                }
            }
        });

        varsha.start();
        harsha.start();
    }
}
