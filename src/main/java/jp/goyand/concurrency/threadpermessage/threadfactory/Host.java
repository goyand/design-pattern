package jp.goyand.concurrency.threadpermessage.threadfactory;

import java.util.concurrent.ThreadFactory;
import jp.goyand.concurrency.threadpermessage.Helper;

public class Host {
  private final Helper helper = new Helper();
  private final ThreadFactory threadFactory;

  public Host(final ThreadFactory threadFactory) {
    this.threadFactory = threadFactory;
  }

  public void request(final int count, final char c) {
    System.out.println("    request(" + count + ", " + c + ") BEGIN");
    threadFactory.newThread(() -> helper.handle(count, c)).start();
    System.out.println("    request(" + count + ", " + c + ") END");
  }
}
