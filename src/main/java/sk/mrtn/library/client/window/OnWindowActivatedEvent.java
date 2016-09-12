package sk.mrtn.library.client.window;

import com.google.gwt.event.shared.GwtEvent;

/**
 * Created by martinliptak on 14/08/16.
 * for comment look at associated interface (if it' there :-))
 */
public class OnWindowActivatedEvent extends GwtEvent<IOnWindowActivatedEventHandler> {
    public static final Type<IOnWindowActivatedEventHandler> TYPE = new Type<>();

    @Override
    public Type<IOnWindowActivatedEventHandler> getAssociatedType() {
        return TYPE;
    }

    @Override
    protected void dispatch(IOnWindowActivatedEventHandler handler) {
        handler.onWindowActivated(this);
    }

    @Override
    public String toString() {
        return OnWindowActivatedEvent.class.getSimpleName();
    }
}