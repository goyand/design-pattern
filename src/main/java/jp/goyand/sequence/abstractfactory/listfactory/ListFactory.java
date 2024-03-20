package jp.goyand.sequence.abstractfactory.listfactory;

import jp.goyand.sequence.abstractfactory.factory.Factory;
import jp.goyand.sequence.abstractfactory.factory.Link;
import jp.goyand.sequence.abstractfactory.factory.Page;
import jp.goyand.sequence.abstractfactory.factory.Tray;

public class ListFactory extends Factory {

    public Link createLink(String caption, String url) {
        return new ListLink(caption, url);
    }

    public Tray createTray(String caption) {
        return new ListTray(caption);
    }

    public Page createPage(String title, String author) {
        return new ListPage(title, author);
    }
}
