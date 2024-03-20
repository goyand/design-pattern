package jp.goyand.sequence.builder;

public class Main {
    public static void main(String[] args) {
        TextBuilder textBuilder = new TextBuilder();
        Director director = new Director(textBuilder);
        director.construct();
        String result = textBuilder.getTextResult();
        System.out.println(result);

//        HTMLBuilder htmlBuilder = new HTMLBuilder();
//        Director director = new Director(htmlBuilder);
//        director.construct();
//        String filename = htmlBuilder.getHTMLResult();
//        System.out.println(filename + "が作成されました。");
    }
}
