package com.github.martinfrank.comicbrowser.xml;

import com.github.martinfrank.comicbrowser.structure.ImageInfo;
import com.github.martinfrank.comicbrowser.structure.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Arrays;
import java.util.stream.Collectors;

@XmlRootElement(name="structure")
public class WebsiteStructure {

    private static final Logger LOGGER = LoggerFactory.getLogger(WebsiteStructure.class);

    @XmlElement(name="title")
    private Title title;

    public WebsiteStructure() {
        title = new Title();
        title.value = "unknown";
    }

    public void writeToFile() throws JAXBException, IOException {
        JAXBContext jaxbContext = JAXBContext.newInstance(WebsiteStructure.class);
        Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
        String outputFileName = generateOutputFileName();
        OutputStream os = new FileOutputStream(outputFileName);
        jaxbMarshaller.marshal( this, os );
        os.close();
        LOGGER.debug("successfully written file {}", outputFileName);
    }

    private String generateOutputFileName() {
        String[] fragments = title.value.split("\\s");
        String filename = Arrays.stream(fragments).collect(Collectors.joining("_"));
        return "output/" + filename + ".xml";
    }

    public void addImageInfo(ImageInfo imageInfo, PageInfo info) {
    }

    public void setTitle(String title) {
        this.title.value = title;
    }


    @XmlRootElement(name="title")
    private static class Title{

        @SuppressWarnings("unused")
        @XmlAttribute(name="value")
        private String value;

    }
}
