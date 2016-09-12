package sk.mrtn.library.client.window;

import com.google.gwt.event.shared.GwtEvent;

/**
 * Created by martinliptak on 14/08/16.
 * for comment look at associated interface (if it' there :-))
 */
public class OnWindowDeactivatedEvent extends GwtEvent<IOnWindowDeactivatedEventHandler> {
    public static final Type<IOnWindowDeactivatedEventHandler> TYPE = new Type<>();

    @Override
    public Type<IOnWindowDeactivatedEventHandler> getAssociatedType() {
        return TYPE;
    }

    @Override
    protected void dispatch(IOnWindowDeactivatedEventHandler handler) {
        handler.onWindowDeactivated(this);
    }

    @Override
    public String toString() {
        return OnWindowDeactivatedEvent.class.getSimpleName();
    }
}