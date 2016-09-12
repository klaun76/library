package sk.mrtn.library.client.develop.console;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.LogRecord;
/**
 * Created by martinliptak on 26/08/15.
 */
public abstract class AConsole extends Handler {
    private static CustomFilter FILTER;
    private static class CustomFilter {
        Set<String> loggerNames;
        public CustomFilter(String... loggerNames) {
            this.loggerNames = new HashSet<>();
            this.loggerNames.addAll(Arrays.asList(loggerNames));
        }
        public boolean isLoggable(LogRecord record) {
            return this.loggerNames.contains(record.getLoggerName());
        }
    }

    public static void enableSpecificLoggers(String... loggers) {
        FILTER = new CustomFilter(loggers);
    }

    public static void enableAllLoggers(){
        FILTER = null;
    }

    protected AConsole(){
        setFormatter(new SimpleTextLogFormatter());
        setLevel(Level.ALL);
    }

    @Override
    public void publish(LogRecord record) {
        if (FILTER != null && !FILTER.isLoggable(record)) {
            return;
        }
        String msg = getFormatter().format(record);
        if (record.getLevel() == Level.SEVERE) {
            error(msg);
        } else if (record.getLevel() == Level.WARNING) {
            warning(msg);
        } else if (record.getLevel() == Level.INFO) {
            info(msg);
        } else {
            log(msg);
        }
    }

    @Override
    public void flush() {
        // No action needed
    }

    @Override
    public void close() {
        // No action needed
    }

    protected abstract void log(String message);
    protected abstract void info(String message);
    protected abstract void warning(String message);
    protected abstract void error(String message);

}

