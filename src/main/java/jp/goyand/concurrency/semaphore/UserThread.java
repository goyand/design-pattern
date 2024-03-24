package jp.goyand.concurrency.semaphore;

import java.util.Random;

public class UserThread extends Thread {
  private static final Random random = new Random(26535);
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
