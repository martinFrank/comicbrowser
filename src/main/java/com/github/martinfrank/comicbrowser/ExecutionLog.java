package com.github.martinfrank.comicbrowser;

import org.slf4j.Logger;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

class ExecutionLog {

    private List<ExecutionLogEntry> executionLogEntries = new ArrayList<>();

    void failed(String errorMessage, IOException e) {
        executionLogEntries.add(ExecutionLogEntry.errorLogEntry(errorMessage, e));
    }

    void message(String msg) {
        executionLogEntries.add(ExecutionLogEntry.messageLogEntry(msg));
    }

    boolean hasFailed(){
        return executionLogEntries.stream().anyMatch(ExecutionLogEntry::hasFailed);
    }


    void debug(Logger logger) {
        for(ExecutionLogEntry entry: executionLogEntries){
            logger.debug("entry{} ",entry);
        }
    }

    boolean hasSucceeded() {
        return ! hasFailed();
    }
}
