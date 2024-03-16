package jp.goyand.pattern.factory.idcard;

import jp.goyand.pattern.factory.framework.Factory;
import jp.goyand.pattern.factory.framework.Product;

public class IDCardFactory extends Factory {
    @Override

    protected Product createProduct(String owner) {
        return new IDCard(owner);
    }

    @Override
    protected void registerProduct(Product product) {
        System.out.println(product + "を登録しました。");
    }
}
