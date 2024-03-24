package jp.goyand.concurrency.batch;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.List;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Consumer;
import java.util.stream.IntStream;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class BatchExecutorTest {

  private List<Integer> dataList;
  private BatchExecutor batchExecutor;
  private AtomicInteger counter;

  @BeforeEach
  void setUp() {
    dataList = IntStream.range(0, 100).boxed().toList();
    batchExecutor = new BatchExecutor();
    counter = new AtomicInteger(0);
  }

  @Test
  public void execute_in_multi_thread() {
    // prepare
    Set<String> concurrentSet = ConcurrentHashMap.newKeySet();

    // execute
    batchExecutor.execute(
        dataList,
        data -> {
          counter.incrementAndGet();
          concurrentSet.add(Thread.currentThread().getName());
        },
        10,
        5);

    // verify
    assertEquals(100, counter.get());
    assertEquals(10, concurrentSet.size());
  }

  @Test
  public void execute_in_multi_thread_with_remaining() {
    // prepare
    Set<String> concurrentSet = ConcurrentHashMap.newKeySet();

    // execute
    batchExecutor.execute(
        dataList,
        data -> {
          counter.incrementAndGet();
          concurrentSet.add(Thread.currentThread().getName());
        },
        10,
        9);

    // verify
    assertEquals(100, counter.get());
    assertEquals(10, concurrentSet.size());
  }

  @Test
  public void execute_in_multi_thread_with_extra_thread() {
    // prepare
    Set<String> concurrentSet = ConcurrentHashMap.newKeySet();

    // execute
    batchExecutor.execute(
        dataList,
        data -> {
          counter.incrementAndGet();
          concurrentSet.add(Thread.currentThread().getName());
        },
        11,
        10);

    // verify
    assertEquals(100, counter.get());
    assertEquals(10, concurrentSet.size());
  }

  @Test
  void throw_exception_individually() {
    // prepare
    Consumer<Integer> executor =
        data -> {
          if (data == 0) throw new RuntimeException("Test exception");
          counter.incrementAndGet();
        };

    // execute & verify
    assertThrows(RuntimeException.class, () -> batchExecutor.execute(dataList, executor, 10, 10));
    assertEquals(99, counter.get());
  }
}
