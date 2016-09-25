package sk.mrtn.library.client.utils.orientationchange.events;

import com.google.gwt.event.shared.GwtEvent;
import sk.mrtn.library.client.utils.orientationchange.WindowStateChangeHandler;

/**
 * Created by martinliptak on 14/08/16.
 * for comment look at associated interface (if it' there :-))
 */
public class OnOrientationChangedEvent extends GwtEvent<IOnOrientationChangedEventHandler> {
    public static final Type<IOnOrientationChangedEventHandler> TYPE = new Type<>();
    private final WindowStateChangeHandler.Orientation orientation;

    public OnOrientationChangedEvent(WindowStateChangeHandler.Orientation orientation) {

        this.orientation = orientation;
    }

    @Override
    public Type<IOnOrientationChangedEventHandler> getAssociatedType() {
        return TYPE;
    }

    @Override
    protected void dispatch(IOnOrientationChangedEventHandler handler) {
        handler.onOrientationChanged(this);
    }

    @Override
    public String toString() {
        return OnOrientationChangedEvent.class.getSimpleName();
    }

    public WindowStateChangeHandler.Orientation getOrientation() {
        return orientation;
    }
}