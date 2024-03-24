package jp.goyand.concurrency.threadpermessage.scheduledexecutorservice;

import jp.goyand.concurrency.threadpermessage.executor.Host;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

public class Main {
    public static void main(String[] args) {
        System.out.println("main BEGIN");
        ScheduledExecutorService executorService = Executors.newScheduledThreadPool(5);
        Host host = new Host(executorService);
        try {
            host.request(10, 'A');
            host.request(20, 'B');
            host.request(30, 'C');
        } finally {
            executorService.shutdown();
            System.out.println("main END");
        }
    }
}