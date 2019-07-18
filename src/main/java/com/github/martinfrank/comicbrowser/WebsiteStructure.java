package com.github.martinfrank.comicbrowser;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

@XmlRootElement(name="structure")
public class WebsiteStructure {

    @SuppressWarnings("unused")
    @XmlElement(name="title")
    private Title title;

    public void writeToFile(File f) throws JAXBException, IOException {
        JAXBContext jaxbContext = JAXBContext.newInstance(WebsiteStructure.class);
        Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
        OutputStream os = new FileOutputStream( "template/employee.xml" );
        jaxbMarshaller.marshal( this, os );
        os.close();
    }


    @XmlRootElement(name="title")
    private static class Title{

        @SuppressWarnings("unused")
        @XmlAttribute(name="value")
        private String value;

    }
}
