package com.github.martinfrank.comicbrowser.xml;

import com.github.martinfrank.comicbrowser.ImageRetriever;
import com.github.martinfrank.comicbrowser.NextPageResolver;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.File;

@XmlRootElement(name="template")
public class WebsiteStructureTemplate {

    public static WebsiteStructureTemplate fromXmlFile(File inputXmlFile) throws JAXBException {
        JAXBContext jaxbContext = JAXBContext.newInstance(WebsiteStructureTemplate.class);
        Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
        WebsiteStructureTemplate template = (WebsiteStructureTemplate) unmarshaller.unmarshal(inputXmlFile);
        template.init();
        return template;
    }

    private void init() {
        nextPageResolver = new NextPageResolver(nextPage, start);
        imageRetriever = new ImageRetriever(image);
    }

    @SuppressWarnings("unused")
    @XmlElement
    private Start start = new Start();

    @SuppressWarnings("unused")
    @XmlElement
    private Cover cover = new Cover();

    @SuppressWarnings("unused")
    @XmlElement
    private Image image = new Image();

    @SuppressWarnings("unused")
    @XmlElement (name="next_page")
    private NextPage nextPage = new NextPage();

    @SuppressWarnings("unused")
    @XmlElement (name = "abort_criteria")
    private AbortCriteria abortCriteria = new AbortCriteria();

    @SuppressWarnings("unused")
    @XmlElement(name = "title")
    private String title = "";


    private NextPageResolver nextPageResolver;
    private ImageRetriever imageRetriever;

    public AbortCriteria getAbortCriteria() {
        return abortCriteria;
    }


    public NextPageResolver getNextPageResolver() {
        return nextPageResolver;
    }

    public ImageRetriever getImageRetriever() {
        return imageRetriever;
    }

    public String getTitle() {
        return title;
    }


    @XmlRootElement(name="cover")
    private static class Cover {

        @SuppressWarnings("unused")
        @XmlAttribute(name="url")
        private String url = "";

        @Override
        public String toString() {
            return "Cover{" +
                    "url='" + url + '\'' +
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
