package sk.mrtn.library.client.utils.orientationchange.events;

import com.google.gwt.event.shared.EventHandler;

/**
 * Created by martinliptak on 14/08/16.
 * Temp event/eventhandler for duplicating
 */
public interface IOnOrientationChangedEventHandler extends EventHandler {
    void onOrientationChanged(OnOrientationChangedEvent event);
}
