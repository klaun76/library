package sk.mrtn.library.client.ui.mainpanel;

import com.google.gwt.event.shared.EventBus;
import com.google.gwt.logging.client.LogConfiguration;
import elemental.client.Browser;
import elemental.css.CSSStyleDeclaration;
import elemental.dom.Node;
import elemental.html.DivElement;
import sk.mrtn.library.client.utils.orientationchange.events.IOnWindowResizedEventHandler;
import sk.mrtn.library.client.utils.orientationchange.events.OnWindowResizedEvent;

import javax.inject.Named;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by martinliptak on 09/10/2016.
 */
public abstract class ARootPanel implements IOnWindowResizedEventHandler {

    protected final EventBus eventBus;
    protected Logger LOG;
    List<PanelRegistration> panelRegistrations;
    protected DivElement mainWrapper;
    protected DivElement mainPanel;
    protected boolean initialized;

    ARootPanel(
            final @Named("Common") EventBus eventBus
    ){
        this.eventBus = eventBus;
        if (LogConfiguration.loggingIsEnabled()) {
            LOG = Logger.getLogger(this.getClass().getSimpleName());
            LOG.setLevel(Level.ALL);
        }
        this.panelRegistrations = new ArrayList<>();
    }

    public IPanelRegistration insert(IResponsivePanel responsivePanel) {
        PanelRegistration panelRegistration = new PanelRegistration(this,responsivePanel);
        return panelRegistration;
    }

    public abstract Node asNode();

    public void initialize() {
        LOG.fine("initialize");
        this.initialized = true;
        createMainWrapper();
        this.eventBus.addHandler(OnWindowResizedEvent.TYPE,this);
    }

    protected void notifyRegistrations(double width, double height) {
        for (PanelRegistration panelRegistration : panelRegistrations) {
            panelRegistration.onResized(width, height);
        }
    }

    public DivElement getMainPanel() {
        if (this.mainPanel == null) {
            throw new NullPointerException(this.getClass().getName() + " is not initialized");
        }
        return mainPanel;
    }

    /**
     * setBackgroundColor is called just for testing purposes
     * @return
     */
    protected void createMainWrapper() {
        this.mainWrapper = Browser.getDocument().createDivElement();
        this.mainWrapper.getStyle().setPosition(CSSStyleDeclaration.Position.ABSOLUTE);
        this.mainWrapper.getStyle().setWidth(100, CSSStyleDeclaration.Unit.PCT);
        this.mainWrapper.getStyle().setHeight(100, CSSStyleDeclaration.Unit.PCT);
        this.mainWrapper.getStyle().setBackgroundColor("darkblue");
        this.mainWrapper.getStyle().setTop(0, CSSStyleDeclaration.Unit.PX);
        this.mainWrapper.getStyle().setLeft(0, CSSStyleDeclaration.Unit.PX);
        this.mainWrapper.setId("mainWrapper");
    }

}
