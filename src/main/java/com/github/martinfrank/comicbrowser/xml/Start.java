package com.github.martinfrank.comicbrowser.xml;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="start")
public class Start {

    @SuppressWarnings("unused")
    @XmlAttribute(name="url")
    public String url;

    @SuppressWarnings("unused")
    @XmlAttribute(name="year")
    public int year;

    @SuppressWarnings("unused")
    @XmlAttribute(name="month")
    public int month;

    @SuppressWarnings("unused")
    @XmlAttribute(name="day")
    public int day;

    @SuppressWarnings("unused")
    @XmlAttribute(name="format")
    public String format;

    @Override
    public String toString() {
        return "Start{" +
                "url='" + url + '\'' +
                '}';
    }
}
