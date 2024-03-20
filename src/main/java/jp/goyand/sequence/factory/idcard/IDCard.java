package jp.goyand.sequence.factory.idcard;

import jp.goyand.sequence.factory.framework.Product;

public class IDCard extends Product {
    private String owner;

    IDCard(String owner) {
        System.out.println(owner + "のカードを作ります。");
        this.owner = owner;
    }

    @Override
    public void use() {
        System.out.println(owner + "のカードを使います。");
    }

    @Override
    public String toString() {
        return "[IDCard: " + owner + "]";
    }

    public String getOwner() {
        return owner;
    }
}
