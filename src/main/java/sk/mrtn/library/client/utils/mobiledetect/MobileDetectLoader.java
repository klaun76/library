package sk.mrtn.library.client.utils.mobiledetect;

import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.ScriptInjector;
import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.TextResource;

import java.util.logging.Logger;

/**
 * Created by martinliptak on 21/04/16.
 */
public interface MobileDetectLoader {

    class Statics {

        private static boolean injected;

        public static void ensureInjected() {
            if (injected) {
                return;
            }

            injected = true;

            final Resources res = GWT.create(Resources.class);
            ScriptInjector.FromString instance = ScriptInjector.fromString(res.jsresource().getText());
            instance.setWindow(ScriptInjector.TOP_WINDOW);
            instance.inject();
            Logger.getLogger("common").fine("Mobile detect loaded");
        }

        protected interface Resources extends ClientBundle {
            @Source("mobile-detect.min.js")
//            @Source("mobile-detect.js")
            TextResource jsresource();
        }

    }

}
