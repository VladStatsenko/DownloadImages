import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class Parsing {
    public static void main(String[] args) {
        String htmlFile = "dataparse/lenta.html";
        String lines = null;
        try {
            lines = Files.readString(Paths.get(htmlFile));
        } catch (IOException e) {
            e.printStackTrace();
        }

        Document doc = Jsoup.parse(lines);
        Elements elements = doc.getElementsByClass("g-picture");

        elements.stream().map(element -> element.attr("src")).forEach(element -> download(element));

    }


    public static void download(String strURL) {
        try {
            String directory = "images";
            String filename = "image.jpg";
            BufferedImage image = null;
            URL url = new URL(strURL);
            image = ImageIO.read(url);
            if (image != null) {

                ImageIO.write(image, "jpg", new File(directory + "/" + url.hashCode()+filename));

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
