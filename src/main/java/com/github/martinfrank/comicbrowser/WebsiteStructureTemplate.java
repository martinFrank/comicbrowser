package com.github.martinfrank.comicbrowser;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;

@XmlRootElement(name="template")
public class WebsiteStructureTemplate {

    public static WebsiteStructureTemplate testMe(File f) throws JAXBException, IOException {
        JAXBContext jaxbContext = JAXBContext.newInstance(WebsiteStructureTemplate.class);
        Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

        WebsiteStructureTemplate template = new WebsiteStructureTemplate();
        template.start = new Start();
        template.start.url = "start";

        template.cover = new Cover();
        template.cover.url = "cover";

        template.image = new Image();
        template.image.xpath = "xpath";

        template.abortCriteria = new AbortCriteria();
        template.abortCriteria.failure = new AbortCriteria.Failure();
        template.abortCriteria.failure.amount = 3;
        template.abortCriteria.failure.next_not_found = true;
        template.abortCriteria.failure.date = "today";

        OutputStream os = new FileOutputStream( "template/employee.xml" );
        jaxbMarshaller.marshal( template, os );
        os.close();
        return null;
    }

    public static WebsiteStructureTemplate fromXmlFile(File inputXmlFile) throws JAXBException, IOException {
        JAXBContext jaxbContext = JAXBContext.newInstance(WebsiteStructureTemplate.class);
        Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
        return (WebsiteStructureTemplate) jaxbUnmarshaller.unmarshal(inputXmlFile);
    }

    public String getStartUrl() {
        return start.url;
    }

    //    URL getImage();
//    URL getNextPage();
//    URL getCover();
//    URL getStartPage();

    @XmlElement
    private Start start;

    @XmlElement
    private Cover cover;

    @XmlElement
    private Image image;

    @XmlElement (name="next_page")
    private NextPage nextPage;

    @XmlElement (name = "abort_criteria")
    private AbortCriteria abortCriteria;

    public String getImageXPath() {
        return image.xpath;
    }

    public boolean isNextPageRetrievedByXPath() {
        return nextPage.getKind() == NextPage.Kind.XPATH;
    }

    public String getNextPageXPath() {
        return nextPage.getXPath();
    }


    @XmlRootElement(name="start")
    private static class Start {
        @XmlAttribute(name="url")
        private String url;

        @Override
        public String toString() {
            return "Start{" +
                    "url='" + url + '\'' +
                    '}';
        }
    }

    @XmlRootElement(name="cover")
    private static class Cover {
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
