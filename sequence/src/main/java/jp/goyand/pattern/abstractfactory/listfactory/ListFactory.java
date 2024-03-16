package jp.goyand.pattern.abstractfactory.listfactory;

import jp.goyand.pattern.abstractfactory.factory.Factory;
import jp.goyand.pattern.abstractfactory.factory.Link;
import jp.goyand.pattern.abstractfactory.factory.Page;
import jp.goyand.pattern.abstractfactory.factory.Tray;

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
