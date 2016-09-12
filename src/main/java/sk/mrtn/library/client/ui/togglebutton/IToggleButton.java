package sk.mrtn.library.client.ui.togglebutton;

import elemental.dom.Element;

/**
 * Created by martinliptak on 14/08/16.
 */
public interface IToggleButton {
    interface IOnToggleButtonStageChangedListener {
        void onToggleButtonStateChanged(boolean enabled);
    }

    boolean isEnabled();

    void setEnabled(boolean enabled);
    IToggleButton addOnToggleButtonStageChangedListener(IOnToggleButtonStageChangedListener listener);
    IToggleButton removeOnToggleButtonStageChangedListener(IOnToggleButtonStageChangedListener listener);
    Element asElement();
}
