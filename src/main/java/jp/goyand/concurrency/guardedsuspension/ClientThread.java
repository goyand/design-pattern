package jp.goyand.concurrency.guardedsuspension;

import java.util.Random;

public class ClientThread extends Thread {
  private final Random random;
  private final RequestQueue requestQueue;

  public ClientThread(RequestQueue requestQueue, String name) {
    super(name);
    this.requestQueue = requestQueue;
    this.random = new Random();
  }

  @Override
  public void run() {
    for (int i = 0; i < 10; i++) {
      Request request = new Request("No." + i);
      System.out.println(Thread.currentThread().getName() + " requests " + request);
      requestQueue.putRequest(request);
      try {
        Thread.sleep(random.nextInt(1000));
      } catch (InterruptedException e) {
      }
    }
  }
}
