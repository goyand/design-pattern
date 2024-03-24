package jp.goyand.concurrency.threadpermessage.scheduledexecutorservice;

import jp.goyand.concurrency.threadpermessage.Helper;

import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Host {
    private final Helper helper = new Helper();
    private final ScheduledExecutorService executor;

    public Host(ScheduledExecutorService executor) {
        this.executor = executor;
    }

    public void request(final int count, final char c) {
        System.out.println("    request(" + count + ", " + c + ") BEGIN");
        executor.schedule(
                () -> helper.handle(count, c),
                3L,
                TimeUnit.SECONDS
        );
        System.out.println("    request(" + count + ", " + c + ") END");
    }
}
