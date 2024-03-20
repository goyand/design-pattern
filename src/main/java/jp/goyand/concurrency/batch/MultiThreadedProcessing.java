package jp.goyand.concurrency.batch;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.Consumer;

public class MultiThreadedProcessing {

    private final ExecutorService  executor;
    private final int chunkSize;
    private final Logger logger = LoggerFactory.getLogger(getClass());

    public MultiThreadedProcessing(int numThreads, int chunkSize) {
        executor = Executors.newFixedThreadPool(numThreads);
        this.chunkSize = chunkSize;
    }

    public <T> void execute(List<T> dataList, Consumer<T> consumer) {
        CountDownLatch latch = new CountDownLatch((int) Math.ceil((double) dataList.size() / chunkSize));
        try {
            List<T> chunk = new ArrayList<>();
            for (int i = 0; i < dataList.size(); i++) {
                if (i % chunkSize == 0 && i != 0) {
                    executor.submit(new DataProcessor<>(chunk, consumer, latch));
                    chunk = new ArrayList<>();
                }
                chunk.add(dataList.get(i));
            }
            if (!chunk.isEmpty()) {
                executor.submit(new DataProcessor<>(chunk, consumer, latch));
            }
            latch.await();
            logger.info("All threads have finished processing. {}", dataList.size());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            executor.shutdown();
        }
    }
}