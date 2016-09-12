package sk.mrtn.library.client.window;

import com.google.gwt.event.shared.HandlerRegistration;

/**
 * Created by martinliptak on 31/08/16.
 */
public interface IWindowStateController {

    void initialize();

    /**
     * Whether the window is active.
     * @return {@code true} if window is active, {@code false} otherwise.
     */
    boolean isActive();

    /**
     * Adds handler to be notified when the window's state changes.
     * @param onWindowStateChanged handler to be added.
     * @return handler registration, through wich the handler can be removed from receiving notifications.
     */
    HandlerRegistration addWindowStateChangedHandler(IWindowStateHandler onWindowStateChanged);

    /**
     * Whether the application is running in a standalone mode.
     * @return {@code true} if the application is in standalone mode, {@code false} otherwise.
     */
    boolean isStandAloneMode();

}
