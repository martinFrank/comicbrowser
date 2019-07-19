package com.github.martinfrank.comicbrowser.structure;

public class DatePageInfo extends PageInfo {

    private final String date;
    public DatePageInfo(String dateFormatted) {
        super();
        this.date = dateFormatted;
    }

    @Override
    public String getDetails() {
        return "date=" + date;
    }
}
