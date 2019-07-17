package com.github.martinfrank.comicbrowser;

public class NextPageResolver {

    public enum Kind {XPATH, DATE}

    public Kind kind;

    public String getNextPage(){
        return "";
    }
}
