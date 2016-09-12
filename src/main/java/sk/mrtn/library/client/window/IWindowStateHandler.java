package sk.mrtn.library.client.window;

/**
 * Created by martinliptak on 31/08/16.
 * Handles window's state change.
 */
public interface IWindowStateHandler {

    /**
     * Called when window's state change.
     * @param active window state.
     */
    void onWindowStateChanged(boolean active);
}
