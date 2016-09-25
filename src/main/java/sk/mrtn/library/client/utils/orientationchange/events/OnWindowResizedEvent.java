package sk.mrtn.library.client.utils.orientationchange.events;

import com.google.gwt.event.shared.GwtEvent;

/**
 * Created by martinliptak on 14/08/16.
 * for comment look at associated interface (if it' there :-))
 */
public class OnWindowResizedEvent extends GwtEvent<IOnWindowResizedEventHandler> {
    public static final Type<IOnWindowResizedEventHandler> TYPE = new Type<>();

    @Override
    public Type<IOnWindowResizedEventHandler> getAssociatedType() {
        return TYPE;
    }

    @Override
    protected void dispatch(IOnWindowResizedEventHandler handler) {
        handler.onWindowResized(this);
    }

    @Override
    public String toString() {
        return OnWindowResizedEvent.class.getSimpleName();
    }
}