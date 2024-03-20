package jp.goyand.concurrency.batch;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.function.Consumer;

public class BatchExecutor {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    public <T> void execute(List<T> dataList, Consumer<T> consumer, int maxThreadCount, int chunkSize) {
        AtomicBoolean hasError = new AtomicBoolean(false);
        int total = dataList.size();
        try {
            MultiThreadedProcessing multiThreadedProcessing = new MultiThreadedProcessing(maxThreadCount, chunkSize);
            multiThreadedProcessing.execute(dataList, (index, data) -> {
                try {
                    logger.info("Start processing: {}/{}", index + 1, total);
                    consumer.accept(data);
                    logger.info("Completed processing: {}/{}", index + 1, total);
                } catch (Exception e) {
                    hasError.set(true);
                    logger.error("Error occurred: {}/{}", index + 1, total);
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
