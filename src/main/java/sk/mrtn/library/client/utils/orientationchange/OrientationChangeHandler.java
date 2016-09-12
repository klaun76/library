package sk.mrtn.library.client.utils.orientationchange;

import com.google.gwt.dom.client.Document;
import com.google.gwt.event.logical.shared.ResizeEvent;
import com.google.gwt.event.logical.shared.ResizeHandler;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.Window;
import sk.mrtn.library.client.utils.orientationchange.events.OnOrientationChangedEvent;

import javax.inject.Inject;
import javax.inject.Named;

/**
 * Created by martinliptak on 14/08/16.
 */
public class OrientationChangeHandler implements ResizeHandler, IOrientationChangeHandler {

    public enum Orientation {
        LANDSCAPE("landscape"),
        PORTRAIT("portrait");

        private final String orientation;

        Orientation(String orientation) {

            this.orientation = orientation;
        }

        public String getOrientation() {
            return orientation;
        }
    }

    private final EventBus eventBus;

    @Inject
    OrientationChangeHandler(
            final @Named("Common") EventBus eventBus
    ){
        this.eventBus = eventBus;
    }

    @Override
    public void setupOrientation() {
        Window.addResizeHandler(this);
        onResize(null);
    }

    private Boolean isPortrait(){
        return Window.getClientHeight() > Window.getClientWidth();
    }

    @Override
    public void onResize(ResizeEvent event) {
        if(isPortrait()){
            Document.get().getBody().addClassName(Orientation.PORTRAIT.getOrientation());
            Document.get().getBody().removeClassName(Orientation.LANDSCAPE.getOrientation());
            this.eventBus.fireEvent(new OnOrientationChangedEvent(Orientation.PORTRAIT));
        } else {
            Document.get().getBody().addClassName(Orientation.LANDSCAPE.getOrientation());
            Document.get().getBody().removeClassName(Orientation.PORTRAIT.getOrientation());
            this.eventBus.fireEvent(new OnOrientationChangedEvent(Orientation.LANDSCAPE));
        }
    }
}
