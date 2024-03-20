package jp.goyand.concurrency.batch;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Consumer;

public class BatchExecutorImpl implements BatchExecutor {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public <T> void execute(List<T> data, Consumer<T> executor, int threadCount, int chunkSize) {
        AtomicBoolean hasError = new AtomicBoolean(false);
        AtomicInteger processed = new AtomicInteger(); // 0
        int total = data.size();
        try {
            MultiThreadedProcessing multiThreadedProcessing = new MultiThreadedProcessing(threadCount, chunkSize);
            multiThreadedProcessing.execute(data, x -> {
                try {
                    logger.info("Start processing: {}/{}", processed.incrementAndGet(), total);
                    executor.accept(x);
                    logger.info("Completed processing: {}/{}", processed.get(), total);
                } catch (Exception e) {
                    hasError.set(true);
                    logger.error("Error occurred: {}/{}", processed, total);
                    e.printStackTrace();
                }
            });
        } catch (Exception e) {
            hasError.set(true);
            e.printStackTrace();
        }

        if (hasError.get()) {
            throw new RuntimeException("Error processing data");
        }
    }
}
