package com.github.martinfrank.comicbrowser.execution;

public interface ExecutionFeedbackHook<T> {

    void notifyFinished(T t);

    ExecutionLog getExecutionLog();
}
