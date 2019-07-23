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

//    public String getUrl() {
//        return url;
//    }
//
//    public int getWidth() {
//        return width;
//    }
//
//    public int getHeight() {
//        return height;
//    }
//
//    public String getDetail() {
//        return detail;
//    }

    @Override
    public String toString() {
//        return "Page{" +
//                "url='" + url + '\'' +
//                ", detail='" + detail + '\'' +
//                ", width=" + width +
//                ", height=" + height +
//                '}';
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getUrl() {
        return url;
    }
}
