package com.lee.renjun.pixa;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.Test;

import java.io.IOException;

public class Jsopu {
    @Test
    public void fun () throws IOException {
        String url = "https://www.ximalaya.com/youshengshu/16244417/96730152";
        String url2 = "https://unsplash.com/search/photos/love";
        String url3 = "https://pixabay.com/zh/images/search/猫/";
        String urlP = "https://pixabay.com/zh/images/search/佛";
        String urlPPPP = "https://www.pexels.com/zh-cn/search/猫/?pagi=4";
        print("Fetching %s...", url);

        Document doc = Jsoup.connect(url).get();
        Elements links = doc.select("a[href]");
        Elements media = doc.select("[src]");
        Elements imports = doc.select("link[href]");

        print("\nMedia: (%d)", media.size());
        for (Element src : media) {
//            if (src.tagName().equals("img") && "cdn.pixabay.com".equals(src.attr("abs:src").substring(8,23)))
            if (src.tagName().equals("img"))
                print(" * %s: <%s> %sx%s (%s) ",
                        src.tagName(), src.attr("abs:src"), src.attr("width"), src.attr("height"),
                        trim(src.attr("alt"), 20));

        }

        print("\nImports: (%d)", imports.size());
        for (Element link : imports) {
            print(" * %s <%s> (%s)", link.tagName(),link.attr("abs:href"), link.attr("rel"));
        }

        print("\nLinks: (%d)", links.size());
        for (Element link : links) {
            print(" * a: <%s>  (%s)", link.attr("abs:href"), trim(link.text(), 35));
        }
    }

    private static void print(String msg, Object... args) {
        System.out.println(String.format(msg, args));
    }

    private static String trim(String s, int width) {
        if (s.length() > width)
            return s.substring(0, width-1) + ".";
        else
            return s;
    }

}
