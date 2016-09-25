package sk.mrtn.library.client.utils.orientationchange;

import com.google.gwt.dom.client.Document;
import com.google.gwt.event.logical.shared.ResizeEvent;
import com.google.gwt.event.logical.shared.ResizeHandler;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.Window;
import sk.mrtn.library.client.utils.orientationchange.events.IOnWindowResizedEventHandler;
import sk.mrtn.library.client.utils.orientationchange.events.OnOrientationChangedEvent;
import sk.mrtn.library.client.utils.orientationchange.events.OnWindowResizedEvent;

import javax.inject.Inject;
import javax.inject.Named;

/**
 * Created by martinliptak on 14/08/16.
 */
public class WindowStateChangeHandler implements ResizeHandler, IWindowStateChangeHandler {

    private boolean portrait;

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
    WindowStateChangeHandler(
            final @Named("Common") EventBus eventBus
    ){
        this.eventBus = eventBus;
    }

    @Override
    public void registerWindowResizeHanlder() {
        Window.addResizeHandler(this);
        // just to have first resize event and css setting
        this.portrait = !isPortrait();
        onResize(null);
    }

    private Boolean isPortrait(){
        return Window.getClientHeight() > Window.getClientWidth();
    }

    @Override
    public void onResize(ResizeEvent event) {
        this.eventBus.fireEvent(new OnWindowResizedEvent());
        boolean currentPortrait = isPortrait();
        if (currentPortrait != this.portrait) {
            this.portrait = currentPortrait;
            if(this.portrait){
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
}
