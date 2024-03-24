package jp.goyand.concurrency.singlethreadexecution;

public class Main {
  static Gate gate = new Gate();

  public static void main(String[] args) {
    System.out.println("Testing Gate, hit CTRL+C to exit.");
    new UserThread("Alice", "Alaska", gate).start();
    new UserThread("Bobby", "Brazil", gate).start();
    new UserThread("Chris", "Canada", gate).start();
  }
}
