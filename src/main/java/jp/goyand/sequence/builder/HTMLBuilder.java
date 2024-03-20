package jp.goyand.sequence.builder;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

public class HTMLBuilder extends Builder {
    private String filename = "untitled.html";
    private StringBuilder sb = new StringBuilder();

    @Override
    public void makeTitle(String title) {
        filename = title + ".html";
        sb.append("<!DOCTYPE html>\n");
        sb.append("<html>\n");
        sb.append("<head>\n");
        sb.append("<title>" + title + "</title>\n");
        sb.append("</head>\n");
        sb.append("<body>\n");
        sb.append("<h1>" + title + "</h1>\n");
        sb.append("\n");
    }

    @Override
    public void makeString(String str) {
        sb.append("<p>" + str + "</p>\n");
        sb.append("\n");
    }

    @Override
    public void makeItems(String[] items) {
        sb.append("<ul>\n");
        for (String item : items) {
            sb.append("<li>" + item + "</li>\n");
        }
        sb.append("</ul>\n");
        sb.append("\n");
    }

    @Override
    public void close() {
        sb.append("</body>\n");
        sb.append("</html>\n");
        try {
            Writer writer = new FileWriter(filename);
            writer.write(sb.toString());
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getHTMLResult() {
        return filename;
    }
}
