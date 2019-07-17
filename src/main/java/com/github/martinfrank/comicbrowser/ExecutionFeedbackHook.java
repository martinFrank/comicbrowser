package com.github.martinfrank.comicbrowser;

public interface ExecutionFeedbackHook<T> {

    void notifyFinished(T t);

    ExecutionLog getExecutionLog();
}
