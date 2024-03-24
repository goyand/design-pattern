package jp.goyand.concurrency.semaphore;

import java.util.Random;
import java.util.concurrent.Semaphore;

public class BoundedResource {
  private final Semaphore semaphore;
  private final int permits;
  private static final Random random = new Random(314159);

  public BoundedResource(int permits) {
    this.semaphore = new Semaphore(permits);
    this.permits = permits;
  }

  public void use() throws InterruptedException {
    semaphore.acquire(permits);
    try {
      doUse();
    } finally {
      semaphore.release(permits);
    }
  }

  protected void doUse() throws InterruptedException {
    Log.println("BEGIN: used = " + (permits - semaphore.availablePermits()));
    Thread.sleep(random.nextInt(500));
    Log.println("END: used = " + (permits - semaphore.availablePermits()));
  }
}
