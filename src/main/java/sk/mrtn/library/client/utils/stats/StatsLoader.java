package sk.mrtn.library.client.utils.stats;

import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.ScriptInjector;
import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.TextResource;

import java.util.logging.Logger;

/**
 * Created by martinliptak on 21/04/16.
 */
public interface StatsLoader {

    class Statics {

        private static boolean injected;
        private static Stats stats;

        public static void ensureInjected() {
            if (injected) {
                return;
            }

            injected = true;

            final Resources res = GWT.create(Resources.class);
            ScriptInjector.FromString instance = ScriptInjector.fromString(res.hammer().getText());
            instance.setWindow(ScriptInjector.TOP_WINDOW);
            instance.inject();
            Logger.getLogger("common").fine("Stats loaded");
        }

        protected interface Resources extends ClientBundle {
            @Source("stats.min.js")
            TextResource hammer();
        }

        /**
         * I had problem with dagger injection
         * so i added classic singleton call here :-(
         * @return
         */
        public static Stats getStats() {
            if (stats == null) {
                ensureInjected();
                stats = new Stats();
            }
            return stats;
        }


    }

}
