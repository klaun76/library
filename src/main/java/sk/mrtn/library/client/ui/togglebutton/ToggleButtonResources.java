package sk.mrtn.library.client.ui.togglebutton;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.CssResource;

/**
 * Created by martinliptak on 14/08/16.
 */
public interface ToggleButtonResources extends ClientBundle {
    ToggleButtonResources impl = GWT.create(ToggleButtonResources.class);

    @Source("togglebutton.gss")
    ToggleButtonResource css();

    interface ToggleButtonResource extends CssResource {
        String toggleButton();
        String toggleButtonSelected();
    }
}
