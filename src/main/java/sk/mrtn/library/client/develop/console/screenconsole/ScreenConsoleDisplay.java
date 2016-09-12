package sk.mrtn.library.client.develop.console.screenconsole;
/**
 * Created by martinliptak on 02/09/15.
 */
/**
 * Igaming2Go - Display element which holds and displays console reports
 * in standard viewport of game, if enabled
 * @author  Martin Liptak
 * @version 1.0
 * @since   2014-08-06
 */


public class ScreenConsoleDisplay {

    public void log(String msg) {

    }

    public void info(String msg) {

    }

    public void warning(String msg) {

    }

    public void error(String msg) {

    }

    public void open() {

    }

    public void close() {

    }
}
//public class ScreenConsoleDisplay extends Composite {
//    private static String NAME = "console";
//    private static ScreenConsoleDisplayUiBinder uiBinder = GWT.create(ScreenConsoleDisplayUiBinder.class);
//
//    interface ScreenConsoleDisplayUiBinder extends UiBinder<Widget, ScreenConsoleDisplay> {
//    }
//
//    private static final ScreenConsoleDisplayResources resources = ScreenConsoleDisplayResources.impl;
//    private boolean onScreen;
//    private TouchDelegate delegate;
//
//    private boolean maximized = true;
//    private boolean hidden = false;
//    private boolean showLineNumbers = true;
//    private int lineNumber = 0;
//    private int maxLineCount = 150;
//
//    @UiField HTMLPanel wrapper;
//    @UiField FlowPanel resizeButton;
//    @UiField FlowPanel closeButton;
//    @UiField FlowPanel hideButton;
//    @UiField InlineLabel hideButtonLabel;
//
//    ScrollPanel scrollPanel;
//    FlowPanel scrollPanelContent;
//
//
//    public ScreenConsoleDisplay() {
//        initWidget(uiBinder.createAndBindUi(this));
//        resources.screenConsoleDisplayStyle().ensureInjected();
//
//        scrollPanelContent = new FlowPanel();
//        //scrollPanelContent.addStyleName(resources.screenConsoleDisplayStyle().matchparent());
//
//        scrollPanel = new ScrollPanel();
//        scrollPanel.addStyleName(resources.screenConsoleDisplayStyle().messagesContainer());
//        scrollPanel.add(scrollPanelContent);
//        wrapper.add(scrollPanel);
//
//        //Resize Button
//        wrapper.add(resizeButton);
//
//        delegate = new TouchDelegate(resizeButton);
//
//        delegate.addTapHandler(new TapHandler() {
//
//            @Override
//            public void onTap(TapEvent event) {
//                System.out.println("TAP HANDLER");
//                if (isMaximized()) {
//                    minimize();
//                } else {
//                    maximize();
//                }
//            }
//        });
//
//        //Close Button
//        wrapper.add(closeButton);
//
//        delegate = new TouchDelegate(closeButton);
//
//        delegate.addTapHandler(new TapHandler() {
//
//            @Override
//            public void onTap(TapEvent event) {
//                close();
//            }
//        });
//
//        //Hide Button
//        wrapper.add(hideButton);
//
//        delegate = new TouchDelegate(hideButton);
//
//        delegate.addTapHandler(new TapHandler() {
//
//            @Override
//            public void onTap(TapEvent event) {
//                hidden = !hidden;
//
//                if(hidden){
//                    hideConsoleContent();
//                }else{
//                    showConsoleContent();
//                }
//            }
//        });
//
//        minimize();
//    }
//
//    // ====================================//
//    // EVENTS
//    // ====================================//
//
//    @Override
//    protected void onAttach() {
//        super.onAttach();
//        initScrollPanel();
//    }
//
//    // ====================================//
//    // PUBLIC METHODS
//    // ====================================//
//
//    public void log(String msg) {
//        addEntry(msg,resources.screenConsoleDisplayStyle().debug());
//    }
//
//    public void info(String msg) {
//        addEntry(msg,resources.screenConsoleDisplayStyle().info());
//    }
//
//    public void warning(String msg) {
//        addEntry(msg,resources.screenConsoleDisplayStyle().warning());
//    }
//
//    public void error(String msg) {
//        addEntry(msg,resources.screenConsoleDisplayStyle().error());
//    }
//
//    public void open() {
//        if (onScreen == true) {return;}
//        onScreen = true;
//        RootPanel.get().add(this);
//    }
//
//    public void close() {
//        if (onScreen == false) {return;}
//        onScreen = false;
//        RootPanel.get().remove(this);
//    }
//
//    // ====================================//
//    // PRIVATE METHODS
//    // ====================================//
//
//    //hide everything except show/hide button
//    private void hideConsoleContent(){
//        closeButton.getElement().getStyle().setProperty("visibility", "hidden");
//        resizeButton.getElement().getStyle().setProperty("visibility", "hidden");
//        scrollPanel.getElement().getStyle().setProperty("visibility", "hidden");
//        addStyleName(resources.screenConsoleDisplayStyle().hidden());
//        hideButtonLabel.setText("Show");
//    }
//
//    private void showConsoleContent(){
//        closeButton.getElement().getStyle().setProperty("visibility", "visible");
//        resizeButton.getElement().getStyle().setProperty("visibility", "visible");
//        scrollPanel.getElement().getStyle().setProperty("visibility", "visible");
//        removeStyleName(resources.screenConsoleDisplayStyle().hidden());
//        hideButtonLabel.setText("Hide");
//    }
//
//    private void addEntry(String msg, String style) {
//        lineNumber++;
//        FlowPanel entryPanel = new FlowPanel();
//        entryPanel.addStyleName(style);
//        //show line numbers?
//        if(showLineNumbers){
//            entryPanel.getElement().setInnerHTML("#" + lineNumber + ":  " + msg);
//        }else{
//            entryPanel.getElement().setInnerHTML(msg);
//        }
//
//        entryPanel.getElement().getStyle().setProperty("pointerEvents", "none");
//        if (scrollPanelContent.getElement().getChildCount() > maxLineCount ) {
//            scrollPanelContent.getElement().removeChild(scrollPanelContent.getElement().getChild(0));
//        }
//        scrollPanelContent.getElement().appendChild(entryPanel.getElement());
////        scrollPanelContent.getElement().insertFirst(entryPanel.getElement());
//        scrollPanel.refresh();
//        int scrollheight = scrollPanelContent.getOffsetHeight();
//        int height = scrollPanel.getOffsetHeight();
//        int scroll = scrollheight - height;
//        if (scroll > 0) {
//            scrollPanel.scrollTo(0,-scroll,-1,false);
//        }
//    };
//
//    protected static native boolean scrollNative(JavaScriptObject element, int scroll) /*-{
////        $wnd.$(element).scrollTop(scroll);
//    }-*/;
//    private void minimize() {
//        maximized = false;
//        System.out.println("minimized");
//        addStyleName(resources.screenConsoleDisplayStyle().minimized());
//        scrollPanel.addStyleName(resources.screenConsoleDisplayStyle().minimized());
//        scrollPanel.refresh();
//    }
//
//    private void maximize() {
//        maximized = true;
//        System.out.println("maximized");
//        removeStyleName(resources.screenConsoleDisplayStyle().minimized());
//        scrollPanel.removeStyleName(resources.screenConsoleDisplayStyle().minimized());
//        scrollPanel.refresh();
//    }
//
//    private boolean isMaximized() {
//        return maximized;
//    }
//
//    private native void console(final String message) /*-{
//        $wnd.console.log(message);
//    }-*/;
//
//    /** Nastavi zakladne nastavenia pre scroll panel */
//    private void initScrollPanel() {
//
//        scrollPanel.setMomentum(false);
//        scrollPanel.setShowHorizontalScrollBar(false);
//        scrollPanel.setShowVerticalScrollBar(false);
//        scrollPanel.setScrollingEnabledX(false);
//        scrollPanel.setScrollingEnabledY(true);
//        scrollPanel.setAutoHandleResize(true);
//        scrollPanel.setBounce(false);
//        scrollPanel.setSnap(false);
////      scrollPanel.setHeight(320 + "px");
//
//
//    }
//
//}

