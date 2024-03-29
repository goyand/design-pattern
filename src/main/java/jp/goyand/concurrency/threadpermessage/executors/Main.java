package jp.goyand.concurrency.threadpermessage.executors;

import java.util.concurrent.Executors;
import jp.goyand.concurrency.threadpermessage.threadfactory.Host;

public class Main {
  public static void main(String[] args) {
    System.out.println("main BEGIN");
    Host host = new Host(Executors.defaultThreadFactory());
    host.request(10, 'A');
    host.request(20, 'B');
    host.request(30, 'C');
    System.out.println("main END");
  }
}
