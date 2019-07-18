package com.github.martinfrank.comicbrowser;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class WebsiteStructureExtractor implements Runnable {

    private static final Logger LOGGER = LoggerFactory.getLogger(WebsiteStructureExtractor.class);

    private final WebsiteStructureTemplate template;
    private final WebsiteStructure structure;
    private final ExecutionFeedbackHook<WebsiteStructure> hook;

    WebsiteStructureExtractor(WebsiteStructureTemplate template, ExecutionFeedbackHook<WebsiteStructure> hook){
        this.template = template;
        this.hook = hook;
        structure = new WebsiteStructure();
    }


    public void run () {
        LOGGER.debug("run");
        String start = template.getStartUrl();
        try {
            Document document = Jsoup.connect(start).get();
            hook.getExecutionLog().message("successfully read start document");
            do {
                boolean readSuccess = template.getImageRetriever().readImage(document);
                if (readSuccess) {
                    String nextPageUrl = template.getNextPageResolver().readNextPageUrl(document);
                    template.getAbortCriteria().checkNextPage(nextPageUrl);
                    document = Jsoup.connect( nextPageUrl).get();
                }
            }while (!template.getAbortCriteria().hasAnyAbortCriteriaMet());
        } catch (IOException e) {
            e.printStackTrace();
            hook.getExecutionLog().failed("IOException e", e);
            hook.notifyFinished(structure);
        }
        hook.getExecutionLog().message("all worked properly");
        hook.notifyFinished(structure);
    }


}
