package com.github.martinfrank.comicbrowser;

class ExecutionLogEntry {

    private boolean hasFailed;
    private String message;
    private Object reason;

    private ExecutionLogEntry(String message, Object reason, boolean hasFailed) {
        this.message = message;
        this.reason = reason;
        this.hasFailed = hasFailed;
    }

    static ExecutionLogEntry errorLogEntry(String message, Object reason) {
        return new ExecutionLogEntry(message, reason, true);
    }

    static ExecutionLogEntry messageLogEntry(String message) {
        return new ExecutionLogEntry(message, "", false);
    }

    boolean hasFailed(){
        return hasFailed;
    }

    @Override
    public String toString() {
        return "ExecutionLogEntry{" +
                "hasFailed=" + hasFailed +
                ", message='" + message + '\'' +
                ", reason=" + reason +
                '}';
    }
}
