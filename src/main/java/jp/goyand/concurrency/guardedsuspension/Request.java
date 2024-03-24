package jp.goyand.concurrency.guardedsuspension;

public record Request(String name) {

    @Override
    public String toString() {
        return "[ Request " + name + " ]";
    }
}
