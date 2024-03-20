package jp.goyand.concurrency.batch;

import java.util.List;
import java.util.function.Consumer;

public interface BatchExecutor {
    <T> void execute(List<T> data, Consumer<T> executor, int threadCount, int chunkSize);
}
