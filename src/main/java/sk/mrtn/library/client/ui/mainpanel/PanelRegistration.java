package sk.mrtn.library.client.ui.mainpanel;

/**
 * Created by martinliptak on 02/10/16.
 */
public class PanelRegistration implements IPanelRegistration {

    private final RootResponsivePanel rootResponsivePanel;
    private final IResponsivePanel responsivePanel;
    private boolean destroyed = false;


    public PanelRegistration(RootResponsivePanel rootResponsivePanel, IResponsivePanel responsivePanel) {
        this.rootResponsivePanel = rootResponsivePanel;
        this.responsivePanel = responsivePanel;
        this.rootResponsivePanel.getMainPanel().appendChild(this.responsivePanel.asNode());
        this.rootResponsivePanel.panelRegistrations.add(this);
    }

    @Override
    public void removePanel() {
        if (this.destroyed) {
            throw new NullPointerException("PanelAlready destroyed");
        }
        this.destroyed = true;
        this.rootResponsivePanel.asNode().removeChild(this.responsivePanel.asNode());
        this.rootResponsivePanel.panelRegistrations.remove(this);
    }

    void onResized(double width, double height) {
        this.responsivePanel.onResized(width,height);
    }
}
