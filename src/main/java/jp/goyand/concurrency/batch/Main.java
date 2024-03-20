package jp.goyand.concurrency.batch;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.stream.IntStream;

public class Main {

    private static final int SIZE = 10_000;
    private static final int BATCH_SIZE = 100;
    private static final int THREAD_COUNT = 10;

    private static final Logger logger = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        BatchExecutor batchExecutor = new BatchExecutor();
        batchExecutor.execute(generateList(SIZE), x -> {
                try {
                    // some heavy processing
                    Thread.sleep(1);
                } catch (InterruptedException ignored) {}
            }, THREAD_COUNT, BATCH_SIZE);
        logger.info("Time taken: {}ms", System.currentTimeMillis() - start);
    }

    private static List<Integer> generateList(int size) {
        return IntStream.range(0, size).boxed().toList();
    }
}
