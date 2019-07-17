package com.github.martinfrank.comicbrowser;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

public class AbortCriteria {

    @XmlElement
    public Failure failure;

    @XmlRootElement(name="failure")
    public static class Failure {

        @XmlAttribute(name="amount")
        public int amount;

        @XmlAttribute(name="date")
        public String date;

        @XmlAttribute(name="next_not_found")
        public boolean next_not_found;

        @Override
        public String toString() {
            return "Failure{" +
                    "amount=" + amount +
                    ", value='" + date + '\'' +
                    ", next_not_found=" + next_not_found +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "AbortCriteria{" +
                "failure=" + failure +
                '}';
    }
}
