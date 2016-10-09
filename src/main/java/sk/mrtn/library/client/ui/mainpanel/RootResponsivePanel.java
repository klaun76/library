package sk.mrtn.library.client.ui.mainpanel;

import com.google.gwt.core.client.Scheduler;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.logging.client.LogConfiguration;
import elemental.client.Browser;
import elemental.css.CSSStyleDeclaration;
import elemental.dom.Node;
import elemental.html.DivElement;
import sk.mrtn.library.client.utils.orientationchange.events.IOnWindowResizedEventHandler;
import sk.mrtn.library.client.utils.orientationchange.events.OnWindowResizedEvent;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by martinliptak on 26/09/16.
 * first version of responsive panel which resizes itself to fit best in
 * given resolution. if its possible to have 16:9 or in worst conditions to fit 4:3
 * TODO: create possibility to set min/max aspect ratio, max size
 */
public class RootResponsivePanel extends ARootPanel implements IOnWindowResizedEventHandler, IRootResponsivePanel {


    private final double maxWidth;
    private final double maxHeight;
    private double minAspectRatio;
    private double maxAspectRatio;

    @Inject
    RootResponsivePanel(
            final @Named("Common") EventBus eventBus
            )
    {
        super(eventBus);
        this.minAspectRatio = 4/3.0;
        this.maxAspectRatio = 16/9.0;
        this.maxWidth = 1280;
        this.maxHeight = 720;
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
        this.mainPanel = createMainPanel();
        this.mainWrapper.appendChild(this.mainPanel);
        onWindowResized(null);
    }

    @Override
    public void onWindowResized(OnWindowResizedEvent event) {
        Scheduler.get().scheduleDeferred(this::onWindowResizedDeferred);
    }

    private void onWindowResizedDeferred() {

        double width = this.mainWrapper.getClientWidth();
        double height = this.mainWrapper.getClientHeight();

        double calculatedWidth = width;
        double calculatedHeight = width / this.maxAspectRatio;

        double deltaY = calculatedHeight - height;
        if (deltaY > 1) {
            calculatedHeight = height;
            calculatedWidth = calculatedHeight * this.maxAspectRatio;
        } else if (deltaY < 1) {
            calculatedHeight = calculatedWidth / this.minAspectRatio;
            if (calculatedHeight > height) calculatedHeight = height;
        }

        double rWidth = Math.round(calculatedWidth);
        double rHeight = Math.round(calculatedHeight);

        if (rWidth > this.maxWidth) {
            rWidth = this.maxWidth;
            rHeight = this.maxHeight;
        }

//        String report = this.mainWrapper.getClientWidth()+" x "+this.mainWrapper.getClientHeight();
//        report += "<br>" + this.minAspectRatio + " x " +this.maxAspectRatio;
//        report += "<br>" + rWidth + " x " +rHeight +" = " + (rWidth/rHeight);
//        this.mainPanel.setInnerHTML(report);

        this.mainPanel.getStyle().setWidth(rWidth, CSSStyleDeclaration.Unit.PX);
        this.mainPanel.getStyle().setHeight(rHeight, CSSStyleDeclaration.Unit.PX);
        this.mainPanel.getStyle().setLeft((width-rWidth)/2, CSSStyleDeclaration.Unit.PX);
        this.mainPanel.getStyle().setTop((height-rHeight)/2, CSSStyleDeclaration.Unit.PX);

        double finalRHeight = rHeight;
        double finalRWidth = rWidth;
        Scheduler.get().scheduleDeferred(() -> {
            notifyRegistrations(finalRWidth, finalRHeight);
        });
    }

    /**
     * setBackgroundColor is called just for testing purposes
     * @return
     */
    private DivElement createMainPanel() {
        DivElement divElement = Browser.getDocument().createDivElement();
        divElement.getStyle().setPosition(CSSStyleDeclaration.Position.ABSOLUTE);
        divElement.getStyle().setBackgroundColor("gray");
        divElement.setId("mainPanel");
        return divElement;
    }

}
