package jp.goyand.concurrency.batch;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.BiConsumer;

public class MultiThreadedProcessing {

    private final ExecutorService executor;
    private final int chunkSize;
    private final Logger logger = LoggerFactory.getLogger(getClass());

    public MultiThreadedProcessing(int maxThreadCount, int chunkSize) {
        executor = Executors.newFixedThreadPool(maxThreadCount);
        this.chunkSize = chunkSize;
    }

    public <T> void execute(List<T> dataList, BiConsumer<Integer, T> consumer) {
        CountDownLatch latch = new CountDownLatch((int) Math.ceil((double) dataList.size() / chunkSize));
        try {
            List<T> chunk = new ArrayList<>();
            for (int i = 0; i < dataList.size(); i++) {
                chunk.add(dataList.get(i));
                if (chunk.size() >= chunkSize) {
                    executor.submit(new DataProcessor<>(chunk, i - chunkSize + 1, consumer, latch));
                    chunk = new ArrayList<>();
                }
            }
            if (!chunk.isEmpty()) {
                executor.submit(new DataProcessor<>(chunk, dataList.size() - chunk.size(), consumer, latch));
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