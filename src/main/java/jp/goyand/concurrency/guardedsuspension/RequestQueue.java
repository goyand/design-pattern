package jp.goyand.concurrency.guardedsuspension;

// import java.util.Queue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

// example 1
// public class RequestQueue {
//    private final Queue<Request> queue = new java.util.LinkedList<>();
//    public synchronized Request getRequest() {
//        while (queue.peek() == null) {
//            try {
//                wait();
//            } catch (InterruptedException e) {
//            }
//        }
//        return queue.remove();
//    }
//    public synchronized void putRequest(Request request) {
//        queue.offer(request);
//        notifyAll();
//    }
// }

// example 2
public class RequestQueue {
  private final BlockingQueue<Request> queue = new LinkedBlockingQueue<>();

  public Request getRequest() {
    Request request = null;
    try {
      request = queue.take();
    } catch (InterruptedException e) {
    }
    return request;
  }

  public void putRequest(Request request) {
    try {
      queue.put(request);
    } catch (InterruptedException e) {
    }
  }
}
