package sk.mrtn.library.client.ui.mainpanel;

import elemental.dom.Node;

/**
 * Created by martinliptak on 26/09/16.
 */
public interface IRootResponsivePanel {

    /**
     * provides self as node which is attached to DOM
     * @return root panel wrapper
     */
    Node asNode();

    /**
     *
     * @param responsivePanel
     */
    IPanelRegistration insert(IResponsivePanel responsivePanel);

}
