package com.github.martinfrank.comicbrowser.xml;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="image")
public class Image {

    @SuppressWarnings("unused")
    @XmlAttribute(name="xpath")
    private String xpath = "";

    @Override
    public String toString() {
        return "Image{" +
                "xpath='" + xpath + '\'' +
                '}';
    }

    public String getXpath() {
        return xpath;
    }
}
