package io.github.jzdayz.database;

import com.p6spy.engine.spy.appender.StdoutLogger;

public class CustomStdoutLogger extends StdoutLogger {
    @Override
    public void logText(String text) {
        System.err.println(text);
    }
}
