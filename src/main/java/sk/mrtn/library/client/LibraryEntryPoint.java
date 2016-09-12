package sk.mrtn.library.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import sk.mrtn.library.client.develop.console.Debugging;
import sk.mrtn.library.client.utils.mobiledetect.MobileDetectLoader;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by martinliptak on 11/08/16.
 */
public class LibraryEntryPoint implements EntryPoint {

    private Logger common;

    @Override
    public void onModuleLoad() {
        Debugging.initialize();
        common = Logger.getLogger("common");
        common.setLevel(Level.INFO);
        Logger.getLogger("common").info("LibraryEntryPoint onModuleLoad");
        MobileDetectLoader.Statics.ensureInjected();
    }
}