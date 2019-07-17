package com.github.martinfrank.comicbrowser;

import javax.xml.bind.annotation.XmlAttribute;

public class NextPage {


    public enum Kind {
        DATE("date"), XPATH("xpath");

        private final String name;

        Kind(String name) {
            this.name = name;
        }

        static Kind byName(String name) {
            for (Kind kind : Kind.values()) {
                if (kind.name.equalsIgnoreCase(name)) {
                    return kind;
                }
            }
            return null;
        }

    }

    @SuppressWarnings("unused")
    @XmlAttribute(name = "kind")
    private String kind;

    @SuppressWarnings("unused")
    @XmlAttribute(name = "xpath")
    private String xpath;


    @Override
    public String toString() {
        return "NextPage{" +
                "kind=" + kind +
                ", xpath='" + xpath + '\'' +
                '}';
    }

    Kind getKind() {
        return Kind.byName(kind);
    }

    String getXPath() {
        return xpath;
    }
}
