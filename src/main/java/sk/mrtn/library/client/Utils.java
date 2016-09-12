package sk.mrtn.library.client;

import com.google.gwt.logging.client.LogConfiguration;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by martinliptak on 21/04/16.
 */
public class Utils {
    public static Logger LOG;
    static {
        if (LogConfiguration.loggingIsEnabled()) {
            LOG = Logger.getLogger("common");
            LOG.setLevel(Level.ALL);
        }
    }
    public static String greet() {
        return "Hello world";
    }
}
