package sk.mrtn.library.client.develop.console;

import com.google.gwt.logging.client.LogConfiguration;
import com.google.gwt.user.client.Window;

/**
 * Created by martinliptak on 02/09/15.
 * TODO: popratat zinjectalizovat ucesat
 * onscreenova konzola nebola nikdy skusana
 */
public class Debugging {

    private static Debugging debugging;

    private Debugging(){
        if (LogConfiguration.loggingIsEnabled() && Window.Location.getParameter("console") != null) {
//            if (Window.Location.getParameter("console").contains("scree")) {
//                ScreenConsole.setConsole();
//            }
            if (Window.Location.getParameter("console").contains("native")) {
                JavascriptConsole.setConsole();
            }

        }
    }

    public static void initialize() {
        if (debugging != null) return;
        debugging = new Debugging();
    }
}
