package com.github.martinfrank.comicbrowser.xml;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

public class AbortCriteria {

    @SuppressWarnings("unused")
    @XmlElement
    private Failure failure = new Failure();

    @XmlElement
    private Success success = new Success();

    private int count = 0;
    private boolean isNextPageNotFoundCriteriaMet = false;
    private int errorCount = 0;
    private int successCount = 0;


    public void increaseSucceedCount() {
        successCount = successCount + 1;
    }

    public boolean hasAnyAbortCriteriaMet() {
        if(isNextPageNotFoundCriteriaMet) {
            return true;
        }
        if(errorCount > failure.amount){
            return true;
        }

        if (successCount >= success.amount) {
            return true;
        }
        return false;
    }

    public void checkNextPage(String nextPageUrl) {
        if (nextPageUrl == null ||nextPageUrl.length()==0 ){
            if(failure.next_not_found){
                isNextPageNotFoundCriteriaMet = true;
            }
            errorCount = errorCount + 1;
        }
    }


    @XmlRootElement(name="failure")
    public static class Failure {

        @XmlAttribute(name="amount")
        int amount;

        @XmlAttribute(name="date")
        String date;

        @XmlAttribute(name="next_not_found")
        boolean next_not_found;

        @Override
        public String toString() {
            return "Failure{" +
                    "amount=" + amount +
                    ", value='" + date + '\'' +
                    ", next_not_found=" + next_not_found +
                    '}';
        }
    }

    @XmlRootElement(name = "success")
    public static class Success {

        @XmlAttribute(name = "amount")
        int amount;
    }

    @Override
    public String toString() {
        return "AbortCriteria{" +
                "failure=" + failure +
                '}';
    }
}
