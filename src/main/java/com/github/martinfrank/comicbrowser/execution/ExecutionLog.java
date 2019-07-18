package com.github.martinfrank.comicbrowser.execution;

import org.slf4j.Logger;

import java.util.ArrayList;
import java.util.List;

public class ExecutionLog {

    private List<ExecutionLogEntry> executionLogEntries = new ArrayList<>();

    public void failed(String errorMessage, Object reason) {
        executionLogEntries.add(ExecutionLogEntry.errorLogEntry(errorMessage, reason));
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
