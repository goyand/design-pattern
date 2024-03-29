package jp.goyand.concurrency.threadpermessage.executor;

import java.util.concurrent.Executor;
import jp.goyand.concurrency.threadpermessage.Helper;

public class Host {
  private final Helper helper = new Helper();
  private final Executor executor;

  public Host(final Executor executor) {
    this.executor = executor;
  }

  public void request(final int count, final char c) {
    System.out.println("    request(" + count + ", " + c + ") BEGIN");
    executor.execute(() -> helper.handle(count, c));
    System.out.println("    request(" + count + ", " + c + ") END");
  }
}
