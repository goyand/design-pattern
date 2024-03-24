package jp.goyand.concurrency.guardedsuspension;

public class Main {
  public static void main(String[] args) {
    RequestQueue requestQueue = new RequestQueue();
    new ClientThread(requestQueue, "Alice").start();
    new ServerThread(requestQueue, "Bobby").start();
  }
}
