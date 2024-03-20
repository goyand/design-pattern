package jp.goyand.concurrency.batch;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;
import java.util.function.Consumer;

class DataProcessor<T> implements Callable<Void> {
    private final List<T> chunk;
    private final Consumer<T> consumer;
    private final CountDownLatch latch;

    private final Logger logger = LoggerFactory.getLogger(getClass());

    public DataProcessor(List<T> chunk, Consumer<T> consumer, CountDownLatch latch) {
        this.chunk = chunk;
        this.consumer = consumer;
        this.latch = latch;
    }

    @Override
    public Void call() {
        try {
            for (T data : chunk) {
                consumer.accept(data);
                logger.info("Processing data: {}", data);
            }
        } finally {
            latch.countDown();
        }
        return null;
    }
}