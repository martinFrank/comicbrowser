package com.github.martinfrank.comicbrowser.xml;


import com.github.martinfrank.comicbrowser.structure.ImageInfo;
import com.github.martinfrank.comicbrowser.structure.PageInfo;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "page")
public class Page {

    @XmlAttribute(name = "url")
    private String url = "";

    @XmlAttribute(name = "width")
    private int width = 0;

    @XmlAttribute(name = "height")
    private int height = 0;

    @XmlAttribute(name = "detail")
    private String detail = "";

    public Page() {
    }

    public Page(ImageInfo imageInfo, PageInfo pageInfo) {
        url = imageInfo.getSrc();
        width = imageInfo.getWidth();
        height = imageInfo.getHeight();
        detail = pageInfo.getDetails();
    }


}
