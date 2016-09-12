package sk.mrtn.library.client.events.temp;

import com.google.gwt.event.shared.GwtEvent;

/**
 * Created by martinliptak on 14/08/16.
 * for comment look at associated interface (if it' there :-))
 */
public class On$$$Event extends GwtEvent<IOn$$$EventHandler> {
    public static final Type<IOn$$$EventHandler> TYPE = new Type<>();

    @Override
    public Type<IOn$$$EventHandler> getAssociatedType() {
        return TYPE;
    }

    @Override
    protected void dispatch(IOn$$$EventHandler handler) {
        handler.on$$$(this);
    }

    @Override
    public String toString() {
        return On$$$Event.class.getSimpleName();
    }
}