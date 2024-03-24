package jp.goyand.concurrency.semaphore;

class Log {
  public static void println(String s) {
    System.out.println(Thread.currentThread().getName() + ": " + s);
  }
}
