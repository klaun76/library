package sk.mrtn.library.client.storage;

/**
 * Created by martinliptak on 17/11/2016.
 * For now just implemented by standard browser storage
 * and can be changed to any other type, for example
 * server storage
 */
public interface IStorage {

    /**
     * clears all keys created with this reference
     */
    void clear();

    /**
     * gets stored item under specified key
     * @param key - key is merged reference and specified key
     * @return
     */
    String getItem(String key);

    /**
     * initializes storage and sets basic reference which
     * should identify application, and do not conflict with other
     * projects within cache.
     * if reference is null then its empty string
     * @param reference - anything for example classpath of project
     */
    void initialize(String reference);

    /**
     * removes item
     * @param key key is merged reference and specified key
     */
    void removeItem(String key);

    /**
     * sets data to specified key
     * @param key
     * @param data
     */
    void setItem(String key, String data);
}
