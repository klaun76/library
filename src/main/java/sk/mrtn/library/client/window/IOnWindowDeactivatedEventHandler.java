package sk.mrtn.library.client.window;

import com.google.gwt.event.shared.EventHandler;

/**
 * Created by martinliptak on 14/08/16.
 * Event occurs when app window is deactivated
 */
public interface IOnWindowDeactivatedEventHandler extends EventHandler {
    void onWindowDeactivated(OnWindowDeactivatedEvent event);
}
