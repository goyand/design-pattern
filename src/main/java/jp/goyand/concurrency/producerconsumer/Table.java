package jp.goyand.concurrency.producerconsumer;

import java.util.concurrent.ArrayBlockingQueue;

// Example 1
// public class Table {
//    private final String[] buffer;
//    private int tail;
//    private int head;
//    private int count;
//    public Table(int count) {
//        this.buffer = new String[count];
//        this.head = 0;
//        this.tail = 0;
//        this.count = 0;
//    }
//    public synchronized void put(String cake) throws InterruptedException {
//        System.out.println(Thread.currentThread().getName() + " puts " + cake);
//        while (count >= buffer.length) {
//            wait();
//        }
//        buffer[tail] = cake;
//        tail = (tail + 1) % buffer.length;
//        count++;
//        notifyAll();
//    }
//    public synchronized String take() throws InterruptedException {
//        while (count <= 0) {
//            wait();
//        }
//        String cake = buffer[head];
//        head = (head + 1) % buffer.length;
//        count--;
//        notifyAll();
//        System.out.println(Thread.currentThread().getName() + " takes " + cake);
//        return cake;
//    }
// }

// Example 2
public class Table extends ArrayBlockingQueue<String> {
  public Table(int count) {
    super(count);
  }

  public void put(String cake) throws InterruptedException {
    System.out.println(Thread.currentThread().getName() + " puts " + cake);
    super.put(cake);
  }

  public String take() throws InterruptedException {
    String cake = super.take();
    System.out.println(Thread.currentThread().getName() + " takes " + cake);
    return cake;
  }
}
