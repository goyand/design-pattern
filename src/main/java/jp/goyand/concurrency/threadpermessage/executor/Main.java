package jp.goyand.concurrency.threadpermessage.executor;


import java.util.concurrent.Executor;

public class Main {
    public static void main(String[] args) {
        System.out.println("main BEGIN");
        Host host = new Host(
                new Executor() {
                    @Override
                    public void execute(Runnable command) {
                        new Thread().start();
                    }
                }
        );
        host.request(10, 'A');
        host.request(20, 'B');
        host.request(30, 'C');
        System.out.println("main END");
    }
}
