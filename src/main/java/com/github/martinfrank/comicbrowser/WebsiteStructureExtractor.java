package com.github.martinfrank.comicbrowser;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
//import org.jsoup.select.Elements;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import us.codecraft.xsoup.Xsoup;

import java.io.IOException;

public class WebsiteStructureExtractor implements Runnable {

    private static final Logger LOGGER = LoggerFactory.getLogger(WebsiteStructureExtractor.class);
    private static final String ATTRIBUTE_WIDTH = "width";
    private static final String ATTRIBUTE_HEIGHT = "height";
    private static final String ATTRIBUTE_SRC = "src";
    private static final String ATTRIBUTE_HREF = "href";
    private static final String HTTP_PREFIX = "http:";

    private final WebsiteStructureTemplate template;
    private final WebsiteStructure structure;
    private final ExecutionFeedbackHook<WebsiteStructure> hook;

    public WebsiteStructureExtractor(WebsiteStructureTemplate template, ExecutionFeedbackHook<WebsiteStructure> hook){
        this.template = template;
        this.hook = hook;
        structure = new WebsiteStructure();
    }


    public void run () {
        LOGGER.debug("start (call)");
        String start = template.getStartUrl();
        try {
            Document document = Jsoup.connect(start).get();
            boolean readSuccess = readImage(document);
            if(readSuccess){
                String nextPageUrl = readNextPageUrl(document);
            }



        } catch (IOException e) {
            e.printStackTrace();
            hook.getExecutionLog().failed("IOException e", e);
            hook.notifyFinished(structure);
        }
        hook.getExecutionLog().message("all worked properly");
        hook.notifyFinished(structure);
    }

    private String readNextPageUrl(Document document) {
        if(template.isNextPageRetrievedByXPath() ){
            Elements nextPageElement = Xsoup.compile(template.getNextPageXPath()).evaluate(document).getElements();
            if (!nextPageElement.toString().isEmpty()) {
                String nextPageUrl = nextPageElement.attr(ATTRIBUTE_HREF);
                LOGGER.debug("nextPageUrl {}", nextPageUrl);
                return nextPageUrl;
            } else {
                LOGGER.debug("empty");
            }
        }
        return null;
    }

    private boolean readImage(Document document) {
        Elements imageElement = Xsoup.compile(template.getImageXPath()).evaluate(document).getElements();
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


}
