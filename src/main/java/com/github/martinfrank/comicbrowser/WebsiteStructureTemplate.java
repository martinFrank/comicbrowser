package com.github.martinfrank.comicbrowser;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;

@XmlRootElement(name="template")
public class WebsiteStructureTemplate {

    static WebsiteStructureTemplate fromXmlFile(File inputXmlFile) throws JAXBException {
        JAXBContext jaxbContext = JAXBContext.newInstance(WebsiteStructureTemplate.class);
        Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
        return (WebsiteStructureTemplate) unmarshaller.unmarshal(inputXmlFile);
    }

    String getStartUrl() {
        if(isNextPageRetrievedByDate()){
            return start.url+getDateFormatted();
        }
        if(isNextPageRetrievedByXPath()){
            return start.url;
        }
        return "";
    }

    private String getDateFormatted() {
        if(currentDate == null){
            initCurrentDate();
        }
        SimpleDateFormat dateFormat = new SimpleDateFormat(start.format);
        return dateFormat.format(currentDate.getTime());
    }

    private void initCurrentDate() {
        currentDate = Calendar.getInstance();
        currentDate.set(Calendar.YEAR, start.year);
        currentDate.set(Calendar.MONTH, start.month);
        currentDate.set(Calendar.DAY_OF_MONTH, start.month);
    }

    @SuppressWarnings("unused")
    @XmlElement
    private Start start;

    @SuppressWarnings("unused")
    @XmlElement
    private Cover cover;

    @SuppressWarnings("unused")
    @XmlElement
    private Image image;

    @SuppressWarnings("unused")
    @XmlElement (name="next_page")
    private NextPage nextPage;

    @SuppressWarnings("unused")
    @XmlElement (name = "abort_criteria")
    private AbortCriteria abortCriteria;

    @SuppressWarnings("unused")
    @XmlElement(name = "title")
    private String title;

    private Calendar currentDate;

    String getImageXPath() {
        return image.xpath;
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

    AbortCriteria getAbortCriteria() {
        return abortCriteria;
    }

    String getNextPageDate() {
        currentDate.add(Calendar.DATE, 1);
        return start.url+getDateFormatted();
    }


    @XmlRootElement(name="start")
    private static class Start {
        @SuppressWarnings("unused")
        @XmlAttribute(name="url")
        private String url;

        @SuppressWarnings("unused")
        @XmlAttribute(name="year")
        private int year;

        @SuppressWarnings("unused")
        @XmlAttribute(name="month")
        private int month;

        @SuppressWarnings("unused")
        @XmlAttribute(name="day")
        private int day;

        @SuppressWarnings("unused")
        @XmlAttribute(name="format")
        private String format;

        @Override
        public String toString() {
            return "Start{" +
                    "url='" + url + '\'' +
                    '}';
        }
    }

    @XmlRootElement(name="cover")
    private static class Cover {

        @SuppressWarnings("unused")
        @XmlAttribute(name="url")
        private String url;

        @Override
        public String toString() {
            return "Cover{" +
                    "url='" + url + '\'' +
                    '}';
        }
    }

    @XmlRootElement(name="image")
    private static class Image {

        @SuppressWarnings("unused")
        @XmlAttribute(name="xpath")
        private String xpath;

        @Override
        public String toString() {
            return "Image{" +
                    "xpath='" + xpath + '\'' +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "WebsiteStructureTemplate{" +
                "start=" + start +
                ", cover=" + cover +
                ", image=" + image +
                ", nextPage=" + nextPage +
                ", abortCriteria=" + abortCriteria +
                '}';
    }
}
