package jp.goyand.sequence.singleton;

public class Singleton {
    private static Singleton singleton = new Singleton();

    private Singleton() {
        System.out.println("インスタンスを生成しました。");
    }

    public static Singleton getInstance() {
        return singleton;
    }
}

// Another implementation
enum SingletonEnum {
    INSTANCE;

    public void print() {
        System.out.println("print");
    }
}
