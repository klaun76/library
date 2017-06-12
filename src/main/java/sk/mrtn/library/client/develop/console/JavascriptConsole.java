package sk.mrtn.library.client.develop.console;
/**
 * Created by martinliptak on 26/08/15.
 */

import java.util.logging.Logger;

public class JavascriptConsole extends AConsole {

    private static JavascriptConsole console;
    public static void setConsole() {
        if (console == null) {
            if (loggable0()) {
                console = new JavascriptConsole();
                Logger.getLogger("").addHandler(console);
                enableAllLoggers();
            }
        }
    }

    protected JavascriptConsole () {
    }
    protected native void log(final String message) /*-{
		$wnd.console.log(message);
	}-*/;

    protected native void info(final String message) /*-{
		if (typeof $wnd.console.debug == "function") {
			$wnd.console.info('%c ' + message, 'color:blue');
		} else {
			$wnd.console.log(message);
		}
	}-*/;

    protected native void warning(final String message) /*-{
		$wnd.console.warn(message);
	}-*/;

    protected native void error(final String message) /*-{
		$wnd.console.error(message);
	}-*/;

    protected static native boolean loggable0() /*-{
		return !!$wnd.console;
	}-*/;

}

