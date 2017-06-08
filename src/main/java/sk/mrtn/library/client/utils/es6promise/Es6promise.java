package sk.mrtn.library.client.utils.es6promise;

import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.ScriptInjector;
import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.TextResource;

import java.util.logging.Logger;

/**
 * Created by Martin Liptak on 06/04/2017.
 */
public interface Es6promise {

    class Statics {

        private static boolean injected;

        public static void ensureInjected() {
            if (injected) {
                return;
            }

            injected = true;

            final Es6promise.Statics.Resources res = GWT.create(Es6promise.Statics.Resources.class);
            ScriptInjector.FromString instance = ScriptInjector.fromString(res.jsresource().getText());
            instance.setWindow(ScriptInjector.TOP_WINDOW);
            instance.inject();
            Logger.getLogger("common").fine("Mobile detect loaded");
        }

        protected interface Resources extends ClientBundle {
            @Source("es6-promise.min.js")
            TextResource jsresource();
        }

    }
}
