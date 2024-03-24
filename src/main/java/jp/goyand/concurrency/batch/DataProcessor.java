package jp.goyand.concurrency.batch;

import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;
import java.util.function.BiConsumer;

class DataProcessor<T> implements Callable<Void> {
  private final List<T> chunk;
  private int index;
  private final BiConsumer<Integer, T> consumer;
  private final CountDownLatch latch;

  public DataProcessor(
      List<T> chunk, int startIndex, BiConsumer<Integer, T> consumer, CountDownLatch latch) {
    this.chunk = chunk;
    this.index = startIndex;
    this.consumer = consumer;
    this.latch = latch;
  }

  @Override
  public Void call() {
    try {
      for (T data : chunk) {
        consumer.accept(index++, data);
      }
    } finally {
      latch.countDown();
    }
    return null;
  }
}
