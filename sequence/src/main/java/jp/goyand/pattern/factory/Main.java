package jp.goyand.pattern.factory;

import jp.goyand.pattern.factory.framework.Factory;
import jp.goyand.pattern.factory.framework.Product;
import jp.goyand.pattern.factory.idcard.IDCardFactory;

public class Main {
    public static void main(String[] args) {
        Factory factory = new IDCardFactory();
        Product card1 = factory.create("Hiroshi Yuki");
        Product card2 = factory.create("Tomura");
        Product card3 = factory.create("Hanako Sato");
        card1.use();
        card2.use();
        card3.use();
    }

}
