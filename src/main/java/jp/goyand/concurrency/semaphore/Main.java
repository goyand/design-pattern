package jp.goyand.concurrency.semaphore;

public class Main {
    public static void main(String[] args) {
        BoundedResource resource = new BoundedResource(3);
        for (int i = 0; i < 10; i++) {
            new UserThread(resource).start();
        }
    }
}
