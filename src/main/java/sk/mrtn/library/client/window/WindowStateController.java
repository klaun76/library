package sk.mrtn.library.client.window;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.logging.client.LogConfiguration;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by martinliptak on 31/08/16.
 */
public class WindowStateController implements IWindowStateController {

    private static Logger LOG;
    static {
        if (LogConfiguration.loggingIsEnabled()) {
            LOG = Logger.getLogger(WindowStateController.class.getSimpleName());
            LOG.setLevel(Level.SEVERE);
        }
    }
    private final EventBus commonEventBus;
    private List<IWindowStateHandler> handlers;
    private boolean active;
    private JavaScriptObject handlerRegistration;

    @Inject
    WindowStateController(
            final @Named("Common") EventBus commonEventBus
            ) {
        this.commonEventBus = commonEventBus;
        this.active = hasFocus();
        initialize();
    }

    @Override
    public void initialize() {
        this.handlerRegistration = initializeListeners();
    }
    @Override
    public boolean isActive() {
        return false;
    }

    @Override
    public HandlerRegistration addWindowStateChangedHandler(final IWindowStateHandler onWindowStateChanged) {
        if (handlers == null) {
            handlers = new ArrayList<>();
        }
        handlers.add(onWindowStateChanged);
        return () -> handlers.remove(onWindowStateChanged);
    }

    @Override
    public boolean isStandAloneMode() {
        return isStandAloneMode0();
    }

    private native boolean isStandAloneMode0() /*-{
        return $wnd.navigator.standalone === true;
    }-*/;

    private void onVisibilityChanged(boolean active) {
        setWindowState(active);
    }

    protected final void setWindowState(boolean active) {
        this.active = active;
        if (LogConfiguration.loggingIsEnabled()) {
            LOG.info(active ? "activated" : "deactivated");
        }
        onWindowStateChange();
        if (this.active) {
            this.commonEventBus.fireEvent(new OnWindowActivatedEvent());
        } else {
            this.commonEventBus.fireEvent(new OnWindowDeactivatedEvent());
        }
    }

    private void onWindowStateChange() {
        if (handlers == null || handlers.isEmpty()) {
            return;
        }

        for (IWindowStateHandler windowStateHandler : handlers) {
            windowStateHandler.onWindowStateChanged(isActive());
        }
    }

    private static native boolean hasFocus() /*-{
        return $doc.hasFocus();
    }-*/;

    protected native JavaScriptObject initializeListeners() /*-{
        var self = this;

        //Visibility and Focus mutable fields
        var hasFocus;
        var visibility;
        var active;

        //ACTIVE handling
        var onWindowActiveChange = function(focus, visibility) {
             var newActive = focus && visibility;
             if (newActive != active) {
                 active = newActive;
                 self.@WindowStateController::setWindowState(Z)(active);
             }
        };

        function onBlur() {
            if (!hasFocus) {
                return;
            }
            hasFocus = false;
            onWindowActiveChange(hasFocus, visibility);
        };

        function onFocus(){
            if (hasFocus) {
                return;
            }
            hasFocus = true;
            onWindowActiveChange(hasFocus, visibility);
        };

        if (navigator.userAgent.match(/Trident/i)) { // check for Internet Explorer
            $doc.onfocusin = onFocus;
            $doc.onfocusout = onBlur;
        } else {
            $wnd.onfocus = onFocus;
            $wnd.onblur = onBlur;
        }

        var hidden, visibilityChange;
        if (typeof $doc.hidden !== "undefined") { // Opera 12.10 and Firefox 18 and later support
          hidden = "hidden";
          visibilityChange = "visibilitychange";
        } else if (typeof $doc.mozHidden !== "undefined") {
          hidden = "mozHidden";
          visibilityChange = "mozvisibilitychange";
        } else if (typeof $doc.msHidden !== "undefined") {
          hidden = "msHidden";
          visibilityChange = "msvisibilitychange";
        } else if (typeof $doc.webkitHidden !== "undefined") {
          hidden = "webkitHidden";
          visibilityChange = "webkitvisibilitychange";
        }

        var handleVisibilityChange = function() {
          if ($doc[hidden]) {
              visibility = false;
              onWindowActiveChange($doc.hasFocus(), visibility);
          } else {
              visibility = true;
              onWindowActiveChange($doc.hasFocus(), visibility);
          }
        }

        var handlerRegistration = {}
        if (typeof $doc.addEventListener === "undefined" ||
          typeof $doc[hidden] === "undefined") {
              //Unsupported
        } else {
          handlerRegistration.visibilityChange = {
            name: visibilityChange,
            listener: handleVisibilityChange
          };
          // Handle page visibility change
          $doc.addEventListener(visibilityChange, handleVisibilityChange, false);
        }

        visibility = !$doc[hidden];
        hasFocus = $doc.hasFocus();
        active = visibility && hasFocus;
        self.@WindowStateController::onVisibilityChanged(Z)(active);

        return handlerRegistration;
    }-*/;

    private native void clearListeners(JavaScriptObject handlerRegistration) /*-{
        if (navigator.userAgent.match(/Trident/i)) { // check for Internet Explorer
            $doc.onfocusin = null;
            $doc.onfocusout = null;
        } else {
            $wnd.onfocus = null;
            $wnd.onblur = null;
        }

        if (handlerRegistration.visibilityChange) {
            $doc.removeEventListener(handlerRegistration.visibilityChange.name, handlerRegistration.visibilityChange.listener);
        }

    }-*/;

}
