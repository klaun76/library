package sk.mrtn.library.client.utils.orientationchange;

/**
 * Created by martinliptak on 14/08/16.
 */
public interface IWindowStateChangeHandler {
    /**
     * initialization of handler by registering to native
     * code and starting listening to changes.
     * also one onResize call is simulated, dunno if good idea
     */
    void registerWindowResizeHanlder();

    enum Orientation {
        LANDSCAPE("landscape"),
        PORTRAIT("portrait");

        private final String orientation;

        Orientation(String orientation) {

            this.orientation = orientation;
        }

        public String getOrientation() {
            return orientation;
        }
    }
}
