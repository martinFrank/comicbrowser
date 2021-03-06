package com.github.martinfrank.comicbrowser.structure;

public class IndexPageInfo implements PageInfo {

    private final int count;
    public IndexPageInfo(int pageCount) {
        super();
        this.count = pageCount;
    }

    @Override
    public String getDetails() {
        return "page:" + count;
    }
}
