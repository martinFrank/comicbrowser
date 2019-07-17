package com.github.martinfrank.comicbrowser;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.xml.bind.JAXBException;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.*;

public class App implements ExecutionFeedbackHook<WebsiteStructure>{

    private static final Logger LOGGER = LoggerFactory.getLogger(App.class);

    private static final int CORE_POOL_SIZE = 1;
    private static final int MAXIMUM_POOL_SIZE = 1;
    private static final long KEEP_ALIVE_TIME = 1L;
    private static final TimeUnit TIME_UNIT = TimeUnit.MILLISECONDS;

    private final BlockingQueue<Runnable> pool = new LinkedBlockingQueue<>();

    private ThreadPoolExecutor threadPoolExecutor =
            new ThreadPoolExecutor(CORE_POOL_SIZE, MAXIMUM_POOL_SIZE, KEEP_ALIVE_TIME, TIME_UNIT, pool);

    private final ExecutionLog executionLog = new ExecutionLog();


    public static void main(String[] args) {
        if (args.length == 1) {
            LOGGER.debug(args[0]);
            File f = new File(args[0]);
            if (f.exists()) {
                new App().doIt(f);

            }
        }
    }

    private void doIt(File f) {
        try {
            WebsiteStructureTemplate template = WebsiteStructureTemplate.fromXmlFile(f);
            LOGGER.debug("template: {}", template);
            threadPoolExecutor.execute(new WebsiteStructureExtractor(template, this));
            LOGGER.debug("execute...");
        } catch (JAXBException | IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void notifyFinished(WebsiteStructure structure) {
        LOGGER.debug("done with strucutre {}", structure);
        executionLog.debug(LOGGER);
        LOGGER.debug("success!?={}",executionLog.hasSucceeded());
        threadPoolExecutor.shutdown();
    }

    @Override
    public ExecutionLog getExecutionLog() {
        return executionLog;
    }
}
