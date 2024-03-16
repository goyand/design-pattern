package jp.goyand.pattern.prototype;

import jp.goyand.pattern.prototype.framework.Manager;
import jp.goyand.pattern.prototype.framework.Product;

public class Main {
    public static void main(String[] args) {
        Manager manager = new Manager();
        UnderlinePen underlinePen = new UnderlinePen('-');
        MessageBox mBox = new MessageBox('*');
        MessageBox sBox = new MessageBox('/');

        manager.register("strong message", underlinePen);
        manager.register("warning box", mBox);
        manager.register("slash box", sBox);

        Product p1 = manager.create("strong message");
        p1.use("Hello World");

        Product p2 = manager.create("warning box");
        p2.use("Hello World");

        Product p3 = manager.create("slash box");
        p3.use("Hello World");
    }
}
