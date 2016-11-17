package sk.mrtn.library.client.storage;

import com.google.gwt.logging.client.LogConfiguration;
import com.google.gwt.storage.client.Storage;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

/**
 * Created by martinliptak on 17/11/2016.
 * TODO: play with providers and remove unecessary if else with codeStorage fallback
 */
public class BrowserStorage implements IStorage {

    private String reference;
    private Storage storage;
    private Map<String ,String> codeStorage;

    public BrowserStorage(){
        this.storage = Storage.getLocalStorageIfSupported();
        if (this.storage == null) {
            if (LogConfiguration.loggingIsEnabled()) {
                Logger.getLogger("common").warning("local storage not supported");
            }
            this.storage = Storage.getSessionStorageIfSupported();
        }
        if (this.storage == null) {
            if (LogConfiguration.loggingIsEnabled()) {
                Logger.getLogger("common").severe("session storage not supported, IStorage disabled");
            }
            this.codeStorage = new HashMap<>();
        }
    }

    @Override
    public void clear() {
        if (this.storage != null) {
            this.storage.clear();
        } else {
            this.codeStorage = new HashMap<>();
        }
    }

    @Override
    public String getItem(String key) {
        if (this.storage != null) {
            return this.storage.getItem(this.reference+key);
        } else {
            return this.codeStorage.get(this.reference+key);
        }
    }

    @Override
    public void initialize(String reference) {
        Logger.getLogger("common").warning("reference: "+reference);
        this.reference = reference;
        if (this.reference == null) {
            this.reference = "";
        }
    }

    @Override
    public void removeItem(String key) {
        if (this.storage != null) {
            this.storage.removeItem(this.reference+key);
        } else {
            this.codeStorage.remove(this.reference+key);
        }
    }

    @Override
    public void setItem(String key, String data) {
        if (this.storage != null) {
            this.storage.setItem(this.reference+key, data);
        } else {
            this.codeStorage.put(this.reference+key, data);
        }
    }
}
