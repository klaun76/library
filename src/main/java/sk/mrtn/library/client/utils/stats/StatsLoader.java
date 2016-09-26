package sk.mrtn.library.client.utils.stats;

import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.ScriptInjector;
import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.TextResource;
import elemental.client.Browser;
import sk.mrtn.library.client.ticker.ITicker;
import sk.mrtn.library.client.utils.IUrlParametersManager;

import java.util.logging.Logger;

/**
 * Created by martinliptak on 21/04/16.
 */
public interface StatsLoader {

    class Statics {

        private static boolean injected;
        private static Stats stats;

        /**
         * temporary method, i did not figure out best logic way of injection
         * and initialization ticker and stats. so for now i created this method
         * as basic possibility how to initialize stats properly, although not
         * automatically
         * @param ticker - should be only one ticker per application to display stats correctly
         * @param urlParametersManager
         */
        public static void initialize(
                final ITicker ticker,
                final IUrlParametersManager urlParametersManager
        ){
            if (stats == null) {
                if (urlParametersManager.getParameter("dstats") == "true") {
                    stats = StatsLoader.Statics.getStats();
                    Browser.getDocument().getBody().appendChild(stats.getDom());
                    ticker.setStats(stats);
                }
            }
        }

        public static void ensureInjected() {
            if (injected) {
                return;
            }

            injected = true;

            final Resources res = GWT.create(Resources.class);
            ScriptInjector.FromString instance = ScriptInjector.fromString(res.javascript().getText());
            instance.setWindow(ScriptInjector.TOP_WINDOW);
            instance.inject();
            Logger.getLogger("common").fine("Stats loaded");
        }

        protected interface Resources extends ClientBundle {
            @Source("stats.min.js")
            TextResource javascript();
        }

        /**
         * I had problem Stats constructor does not return instanceof Stats
         * so cast fails when injection is in process
         * @return
         */
        public static Stats getStats() {
            if (stats == null) {
                ensureInjected();
                stats = create();
            }
            return stats;
        }

        private static native Stats create() /*-{
            return new $wnd.Stats();
        }-*/;

    }

}
