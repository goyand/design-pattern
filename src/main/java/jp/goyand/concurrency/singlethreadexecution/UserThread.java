package jp.goyand.concurrency.singlethreadexecution;

public class UserThread extends Thread {
  private final Gate gate;
  private final String myname;
  private final String myaddress;

  public UserThread(String myname, String myaddress, Gate gate) {
    this.myname = myname;
    this.myaddress = myaddress;
    this.gate = gate;
  }

  public void run() {
    System.out.println(myname + " BEGIN");
    for (int i = 0; i < 1_000_000; i++) {
      gate.pass(myname, myaddress);
    }
  }
}
