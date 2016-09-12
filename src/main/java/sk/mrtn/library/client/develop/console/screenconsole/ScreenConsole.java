package sk.mrtn.library.client.develop.console.screenconsole;
/**
 * Igaming2Go - Log Handler prints log to ScreenConsoleDisplay
 * @author  Martin Liptak
 * @version 1.0
 * @since   2014-08-06
 */


import sk.mrtn.library.client.develop.console.AConsole;

/**
 * pridanie noveho handlera do standardneho logovania aby tato konzola
 * zacala zobrazovat secko
 * java.util.logging.Logger.getLogger("").addHandler((Handler)CONSOLE);
 * @author martinliptak
 *
 */
public class ScreenConsole extends AConsole {

    private static ScreenConsole console;

    public static void setConsole() {
        if (console == null) {
            console = new ScreenConsole();
            java.util.logging.Logger.getLogger("").addHandler(console);
        }
    }

    private ScreenConsoleDisplay display;
    private ScreenConsole() {
        display = new ScreenConsoleDisplay();
        openConsole();
    }
    protected void log(String msg) {
        display.log(msg);
    }

    protected void info(String msg) {
        display.info(msg);
    }

    protected void warning(String msg) {
        display.warning(msg);
    }

    protected void error(String msg) {
        display.error(msg);
    }

    public void openConsole() {
        display.open();
    }

    public void closeConsole() {
        display.close();
    }


}
