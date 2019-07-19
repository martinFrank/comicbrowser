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
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name="structure")
public class WebsiteStructure {

    private static final Logger LOGGER = LoggerFactory.getLogger(WebsiteStructure.class);

    @XmlElement(name="title")
    private Title title = new Title();

    @XmlElementWrapper(name = "pages")
    @XmlElement(name = "page")
    private List<Page> pages = new ArrayList<>();

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
        String filename = String.join("_", fragments);
        return "output/" + filename + ".xml";
    }

    public void addPage(ImageInfo image, PageInfo info) {
        pages.add(new Page(image, info));
    }

    public void setTitle(String title) {
        this.title.value = title;
    }


    @XmlRootElement(name="title")
    private static class Title{

        @SuppressWarnings("unused")
        @XmlAttribute(name="value")
        private String value = "";

    }
}
