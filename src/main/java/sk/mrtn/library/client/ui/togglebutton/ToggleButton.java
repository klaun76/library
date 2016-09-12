package sk.mrtn.library.client.ui.togglebutton;

import elemental.client.Browser;
import elemental.dom.Element;
import elemental.events.Event;
import elemental.events.EventListener;
import elemental.html.DivElement;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by martinliptak on 14/08/16.
 */
public class ToggleButton implements EventListener, IToggleButton {

    private final List<IOnToggleButtonStageChangedListener> listeners;
    private final ToggleButtonResources RESOURCES = ToggleButtonResources.impl;

    @Override
    public boolean isEnabled() {
        return enabled;
    }

    private boolean enabled;
    private DivElement divElement;

    @Inject
    ToggleButton(){
        this.listeners = new ArrayList<>();
        this.enabled = false;
    }

    @Override
    public IToggleButton addOnToggleButtonStageChangedListener(IOnToggleButtonStageChangedListener listener) {
        if (!this.listeners.contains(listener)) {
            this.listeners.add(listener);
        }
        return this;
    }

    @Override
    public IToggleButton removeOnToggleButtonStageChangedListener(IOnToggleButtonStageChangedListener listener) {
        if (this.listeners.contains(listener)) {
            this.listeners.remove(listener);
        }
        return this;
    }

    @Override
    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
        updateVisual();
    }

    private void updateVisual() {
        if (this.enabled) {
            this.divElement.getClassList().add(RESOURCES.css().toggleButtonSelected());
        } else {
            this.divElement.getClassList().remove(RESOURCES.css().toggleButtonSelected());
        }
    }

    @Override
    public void handleEvent(Event evt) {
        this.enabled = !this.enabled;
        updateVisual();
        for (IOnToggleButtonStageChangedListener listener : this.listeners) {
            listener.onToggleButtonStateChanged(this.enabled);
        }

    }

    @Override
    public Element asElement() {
        if (this.divElement == null) {
            divElement = Browser.getDocument().createDivElement();
            divElement.appendChild(Browser.getDocument().createButtonElement());
            divElement.setClassName(RESOURCES.css().toggleButton());
            divElement.addEventListener(Event.CLICK,this,false);
            RESOURCES.css().ensureInjected();
        }
        return this.divElement;
    }
}
