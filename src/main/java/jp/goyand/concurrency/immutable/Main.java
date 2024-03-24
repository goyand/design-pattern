package jp.goyand.concurrency.immutable;

public class Main {
  public static void main(String[] args) {
    System.out.println("Testing immutable, hit CTRL+C to exit.");
    Person alice = new Person("Alice", "Alaska");
    new PrintPersonThread(alice).start();
    new PrintPersonThread(alice).start();
    new PrintPersonThread(alice).start();
  }
}
