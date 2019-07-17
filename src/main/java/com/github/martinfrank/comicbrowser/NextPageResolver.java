package com.github.martinfrank.comicbrowser;

@SuppressWarnings("unused")
public class NextPageResolver {

    public enum Kind {XPATH, DATE}

    public Kind kind;

    public String getNextPage(){
        return "";
    }
}
