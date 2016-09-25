package sk.mrtn.library.client.utils.orientationchange.events;

import com.google.gwt.event.shared.EventHandler;

/**
 * Created by klaun 25.9.2016
 * Event is fired when browser window is resized
 */
public interface IOnWindowResizedEventHandler extends EventHandler {
    void onWindowResized(OnWindowResizedEvent event);
}
