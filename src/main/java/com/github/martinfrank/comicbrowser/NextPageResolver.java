package com.github.martinfrank.comicbrowser;

import com.github.martinfrank.comicbrowser.xml.NextPage;
import com.github.martinfrank.comicbrowser.xml.Start;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import us.codecraft.xsoup.Xsoup;

import java.text.SimpleDateFormat;
import java.util.Calendar;

@SuppressWarnings("unused")
public class NextPageResolver {

    private static final Logger LOGGER = LoggerFactory.getLogger(NextPageResolver.class);
    private static final String ATTRIBUTE_HREF = "href";

    private final NextPage nextPage;
    private final Start start;

    private Calendar currentDate;

    public NextPageResolver(NextPage nextPage, Start start) {
        this.nextPage = nextPage;
        this.start = start;
        initCurrentDate();
    }

    public String getStartUrl() {
        if(isNextPageRetrievedByDate()){
            return start.url+getDateFormatted();
        }
        if(isNextPageRetrievedByXPath()){
            return start.url;
        }
        return "";
    }


    String readNextPageUrl(Document document) {
        if(isNextPageRetrievedByXPath() ){
            Elements nextPageElement = Xsoup.compile(getNextPageXPath()).evaluate(document).getElements();
            if (!nextPageElement.toString().isEmpty()) {
                String nextPageUrl = nextPageElement.attr(ATTRIBUTE_HREF);
                LOGGER.debug("nextPageUrl {}", nextPageUrl);
                return nextPageUrl;
            } else {
                LOGGER.debug("empty");
            }
        }
        if (isNextPageRetrievedByDate()){
            String nextPageUrl = getNextPageDate();
            LOGGER.debug("nextPageUrl {}", nextPageUrl);
            return nextPageUrl;
        }
        return null;
    }

    boolean isNextPageRetrievedByXPath() {
        return nextPage.getKind() == NextPage.Kind.XPATH;
    }

    boolean isNextPageRetrievedByDate() {
        return nextPage.getKind() == NextPage.Kind.DATE;
    }

    String getNextPageXPath() {
        return nextPage.getXPath();
    }

    String getNextPageDate() {
        currentDate.add(Calendar.DATE, 1);
        return start.url+getDateFormatted();
    }

    private void initCurrentDate() {
        currentDate = Calendar.getInstance();
        currentDate.set(Calendar.YEAR, start.year);
        currentDate.set(Calendar.MONTH, start.month);
        currentDate.set(Calendar.DAY_OF_MONTH, start.month);
    }

    private String getDateFormatted() {
        if(currentDate == null){
            initCurrentDate();
        }
        SimpleDateFormat dateFormat = new SimpleDateFormat(start.format);
        return dateFormat.format(currentDate.getTime());
    }
}
