package jp.goyand.concurrency.immutable;

public class PrintPersonThread extends Thread {
    private Person person;

    public PrintPersonThread(Person person) {
        this.person = person;
    }

    @Override
    public void run() {
        for (int i = 0; i < 10_000; i++) {
            System.out.println(Thread.currentThread().getName() + " prints " + person);
        }
    }
}
