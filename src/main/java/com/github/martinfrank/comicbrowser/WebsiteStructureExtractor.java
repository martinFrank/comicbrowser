package com.github.martinfrank.comicbrowser;

import com.github.martinfrank.comicbrowser.execution.ExecutionFeedbackHook;
import com.github.martinfrank.comicbrowser.structure.ImageInfo;
import com.github.martinfrank.comicbrowser.xml.AbortCriteria;
import com.github.martinfrank.comicbrowser.xml.WebsiteStructure;
import com.github.martinfrank.comicbrowser.xml.WebsiteStructureTemplate;
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
        ImageRetriever imageRetriever = template.getImageRetriever();
        NextPageResolver nextPageResolver = template.getNextPageResolver();
        AbortCriteria abortCriteria = template.getAbortCriteria();
        structure.setTitle(template.getTitle());
        String start = nextPageResolver.getStartUrl();
        try {
            Document document = Jsoup.connect(start).get();
            hook.getExecutionLog().message("successfully read start document");
            do {
                ImageInfo imageInfo = imageRetriever.readImage(document);
                if (imageInfo != null) {
                    structure.addPage(imageInfo, nextPageResolver.getPageInfo());
                    String nextPageUrl = nextPageResolver.readNextPageUrl(document);
                    hook.getExecutionLog().message("next page would be " + nextPageUrl);
                    abortCriteria.checkNextPage(nextPageUrl);
                    abortCriteria.increaseSucceedCount();
                    if (nextPageUrl == null) {
                        break;
                    }
                    document = Jsoup.connect( nextPageUrl).get();
                }
            }while (!abortCriteria.hasAnyAbortCriteriaMet());
        } catch (IOException e) {
            LOGGER.debug("error during execution: {}", e);
            hook.getExecutionLog().failed("IOException e", e);
            hook.notifyFinished(structure);
        }
        hook.getExecutionLog().message("all worked properly");
        hook.notifyFinished(structure);

    }


}
