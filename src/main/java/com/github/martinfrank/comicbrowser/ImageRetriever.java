package com.github.martinfrank.comicbrowser;

import com.github.martinfrank.comicbrowser.xml.Image;
import com.github.martinfrank.comicbrowser.xml.WebsiteStructureTemplate;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import us.codecraft.xsoup.Xsoup;

public class ImageRetriever {

    private static final Logger LOGGER = LoggerFactory.getLogger(ImageRetriever.class);

    private static final String ATTRIBUTE_WIDTH = "width";
    private static final String ATTRIBUTE_HEIGHT = "height";
    private static final String ATTRIBUTE_SRC = "src";
    private static final String HTTP_PREFIX = "http:";

    private final Image image;

    public ImageRetriever(Image image){
        this.image = image;
    }

    public boolean readImage(Document document) {
        LOGGER.debug("read image from xpath {}", getImageXPath());
        Elements imageElement = Xsoup.compile(getImageXPath()).evaluate(document).getElements();
        if (!imageElement.toString().isEmpty()) {
            int width = getInt(imageElement.attr(ATTRIBUTE_WIDTH));
            int height = getInt(imageElement.attr(ATTRIBUTE_HEIGHT));
            String src = HTTP_PREFIX + imageElement.attr(ATTRIBUTE_SRC);
            LOGGER.debug("w/h: {}/{} src={}", width, height, src);
            return true;
        } else {
            LOGGER.debug("empty");
        }
        return false;
    }

    private int getInt(String src) {
        try{
            return Integer.parseInt(src);
        }catch (NumberFormatException | NullPointerException e){
            return 0;
        }
    }

    String getImageXPath() {
        return image.getXpath();
    }
}
