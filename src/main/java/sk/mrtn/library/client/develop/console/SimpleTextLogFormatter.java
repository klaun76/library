package sk.mrtn.library.client.develop.console;
/**
 * Created by martinliptak on 26/08/15.
 */
import java.util.logging.Formatter;
import java.util.logging.LogRecord;

class SimpleTextLogFormatter extends Formatter{

    @Override
    public String format(LogRecord record) {
        StringBuilder s = new StringBuilder();
        s.append("("+record.getLoggerName()+")");
        s.append(": ");
        s.append(record.getMessage());
        return s.toString();
    }

}
