package jp.goyand.pattern.prototype.framework;

import java.util.HashMap;
import java.util.Map;

public class Manager {
    private Map<String, Product> showcase = new HashMap<>();

    public void register(String name, Product proto) {
        showcase.put(name, proto);
    }

    public Product create(String productName) {
        Product p = showcase.get(productName);
        return p.createCopy();
    }
}
