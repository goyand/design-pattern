package jp.goyand.sequence.factory.idcard;

import jp.goyand.sequence.factory.framework.Factory;
import jp.goyand.sequence.factory.framework.Product;

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
