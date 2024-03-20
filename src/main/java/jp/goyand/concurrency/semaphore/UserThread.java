package jp.goyand.concurrency.semaphore;

import java.util.Random;

public class UserThread extends Thread {
    private final static Random random = new Random(26535);
    private final BoundedResource resource;

    public UserThread(BoundedResource resource) {
        this.resource = resource;
    }

    public void run() {
        try {
            while (true) {
                resource.use();
                Thread.sleep((long) (Math.random() * 3000));
            }
        } catch (InterruptedException e) {
        }
    }
}
