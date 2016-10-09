package sk.mrtn.library.client.ui.mainpanel;

import com.google.gwt.core.client.Scheduler;
import com.google.gwt.event.shared.EventBus;
import elemental.client.Browser;
import elemental.css.CSSStyleDeclaration;
import elemental.dom.Node;
import elemental.html.DivElement;
import sk.mrtn.library.client.utils.orientationchange.events.IOnWindowResizedEventHandler;
import sk.mrtn.library.client.utils.orientationchange.events.OnWindowResizedEvent;

import javax.inject.Inject;
import javax.inject.Named;

/**
 * Created by martinliptak on 26/09/16.
 * first version of responsive panel which resizes itself to fit best in
 * given resolution. if its possible to have 16:9 or in worst conditions to fit 4:3
 * TODO: create possibility to set min/max aspect ratio, max size
 */
public class RootStretchingResponsivePanel extends ARootPanel implements IOnWindowResizedEventHandler, IRootResponsivePanel {


    @Inject
    RootStretchingResponsivePanel(
            final @Named("Common") EventBus eventBus
            )
    {
        super(eventBus);
    }

    @Override
    public Node asNode() {
        if (!this.initialized) {
            initialize();
        }
        return this.mainWrapper;
    }

    @Override
    public void initialize() {
        super.initialize();
        this.mainPanel = mainWrapper;
        onWindowResized(null);
    }

    @Override
    public void onWindowResized(OnWindowResizedEvent event) {
        Scheduler.get().scheduleDeferred(this::onWindowResizedDeferred);
    }

    private void onWindowResizedDeferred() {

        double width = this.mainWrapper.getClientWidth();
        double height = this.mainWrapper.getClientHeight();
        Scheduler.get().scheduleDeferred(() -> {
            notifyRegistrations(width, height);
        });
    }

}
