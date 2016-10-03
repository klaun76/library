package sk.mrtn.library.client.ui.mainpanel;

import elemental.dom.Node;

/**
 * Created by martinliptak on 29/09/16.
 */
public interface IResponsivePanel {

    /**
     * method allows parent node to update child on resized
     * @param width - new width of parent
     * @param height - new height of parent
     */
    void onResized(double width, double height);

    /**
     * provides self as node which is attached to parent
     * @return
     */
    Node asNode();
}
