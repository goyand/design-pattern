package jp.goyand.pattern.abstractfactory;

import jp.goyand.pattern.abstractfactory.factory.Factory;
import jp.goyand.pattern.abstractfactory.factory.Link;
import jp.goyand.pattern.abstractfactory.factory.Page;
import jp.goyand.pattern.abstractfactory.factory.Tray;

public class Main {
    public static void main(String[] args) {
        String filename = "list.html";
        String classname = "jp.goyand.pattern.abstractfactory.listfactory.ListFactory";

        Factory factory = Factory.getFactory(classname);

        Link blog1 = factory.createLink("Blog 1", "XXXXXXXXXXXXXXXXX");
        Link blog2 = factory.createLink("Blog 2", "XXXXXXXXXXXXXXXXX");
        Link blog3 = factory.createLink("Blog 3", "XXXXXXXXXXXXXXXXX");

        Tray blogTray = factory.createTray("Blog Site");
        blogTray.add(blog1);
        blogTray.add(blog2);
        blogTray.add(blog3);

        Link news1 = factory.createLink("News 1", "XXXXXXXXXXXXXXXXX");
        Link news2 = factory.createLink("News 2", "XXXXXXXXXXXXXXXXX");
        Tray news3 = factory.createTray("News 3");
        news3.add(factory.createLink("News 3 (US)", "XXXXXXXXXXXXXXXXX"));
        news3.add(factory.createLink("News 3 (UK)", "XXXXXXXXXXXXXXXXX"));

        Tray newsTray = factory.createTray("News Site");
        newsTray.add(news1);
        newsTray.add(news2);
        newsTray.add(news3);

        Page page = factory.createPage(filename, "XXXXXXXXXXXXXXXXX");
        page.add(blogTray);
        page.add(newsTray);

        page.output(filename);
    }
}
