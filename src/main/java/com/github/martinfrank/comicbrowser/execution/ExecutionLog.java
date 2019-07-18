package com.github.martinfrank.comicbrowser.execution;

import org.slf4j.Logger;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ExecutionLog {

    private List<ExecutionLogEntry> executionLogEntries = new ArrayList<>();

    public void failed(String errorMessage, IOException e) {
        executionLogEntries.add(ExecutionLogEntry.errorLogEntry(errorMessage, e));
    }

    public void message(String msg) {
        executionLogEntries.add(ExecutionLogEntry.messageLogEntry(msg));
    }

    boolean hasFailed(){
        return executionLogEntries.stream().anyMatch(ExecutionLogEntry::hasFailed);
    }


    public void debug(Logger logger) {
        for(ExecutionLogEntry entry: executionLogEntries){
            logger.debug("entry{} ",entry);
        }
    }

    public boolean hasSucceeded() {
        return ! hasFailed();
    }
}
