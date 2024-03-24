package jp.goyand.concurrency.readwritelock;

import java.util.concurrent.locks.ReadWriteLock;

// Example 1
//public class Data {
//    private final char[] buffer;
//    private final ReadWriteLock lock = new ReadWriteLock();
//
//    public Data(int size) {
//        this.buffer = new char[size];
//        for (int i = 0; i < buffer.length; i++) {
//            buffer[i] = '*';
//        }
//    }
//
//    public char[] read() throws InterruptedException {
//        lock.readLock();
//        try {
//            return doRead();
//        } finally {
//            lock.readUnlock();
//        }
//    }
//
//    public void write(char c) throws InterruptedException {
//        lock.writeLock();
//        try {
//            doWrite(c);
//        } finally {
//            lock.writeUnlock();
//        }
//    }
//
//    private char[] doRead() {
//        char[] newbuf = new char[buffer.length];
//        for (int i = 0; i < buffer.length; i++) {
//            newbuf[i] = buffer[i];
//        }
//        slowly();
//        return newbuf;
//    }
//
//    private void doWrite(char c) {
//        for (int i = 0; i < buffer.length; i++) {
//            buffer[i] = c;
//            slowly();
//        }
//    }
//
//    private void slowly() {
//        try {
//            Thread.sleep(50);
//        } catch (InterruptedException e) {
//        }
//    }
//}

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

// Example 2
public class Data {
    private final char[] buffer;
    private final ReadWriteLock lock = new ReentrantReadWriteLock(true /* fair */);
    private final Lock readLock = lock.readLock();
    private final Lock writeLock = lock.writeLock();

    public Data(int size) {
        this.buffer = new char[size];
        for (int i = 0; i < buffer.length; i++) {
            buffer[i] = '*';
        }
    }
    public char[] read() throws InterruptedException {
        readLock.lock();
        try {
            return doRead();
        } finally {
            readLock.unlock();
        }
    }
    public void write(char c) throws InterruptedException {
        writeLock.lock();
        try {
            doWrite(c);
        } finally {
            writeLock.unlock();
        }
    }
    private char[] doRead() {
        char[] newbuf = new char[buffer.length];
        for (int i = 0; i < buffer.length; i++) {
            newbuf[i] = buffer[i];
        }
        slowly();
        return newbuf;
    }
    private void doWrite(char c) {
        for (int i = 0; i < buffer.length; i++) {
            buffer[i] = c;
            slowly();
        }
    }
    private void slowly() {
        try {
            Thread.sleep(50);
        } catch (InterruptedException e) {
        }
    }
}