package sk.mrtn.library.client.utils;

/**
 * Created by martinliptak on 02/05/16.
 */

import com.google.gwt.dom.client.Document;
import elemental.dom.Element;
import com.google.gwt.dom.client.Style;
import com.google.gwt.event.shared.EventHandler;

public class CssAnimation {

    private static String ANIMATION_END = "animationend";
    private static String TRANSITION_END = "transitionend";

    static {
        Style style = Document.get().createDivElement().getStyle();
        if (!"".equals(style.getProperty("animation")) && "".equals(style.getProperty("WebkitAnimation"))) {
            ANIMATION_END = "webkitAnimationEnd";
        }
        if (!"".equals(style.getProperty("transition")) && "".equals(style.getProperty("WebkitTransition"))) {
            TRANSITION_END = "webkitTransitionEnd";
        }
    }

    @FunctionalInterface
    public interface IAnimationEndEventHandler extends EventHandler {
        void onAnimationEnd();
    }

    @FunctionalInterface
    public interface ITransitionEndEventHandler extends EventHandler {
        void onTransitionEnd();
    }

    public native static void registerAnimationEndHandler(
            final Element pElement,
            final IAnimationEndEventHandler pHandler) /*-{
        var callback = function(){
            pHandler.@sk.mrtn.library.client.utils.CssAnimation.IAnimationEndEventHandler::onAnimationEnd()();
            pElement.removeEventListener(@sk.mrtn.library.client.utils.CssAnimation::ANIMATION_END, callback, false);
        }
        pElement.addEventListener(@sk.mrtn.library.client.utils.CssAnimation::ANIMATION_END, callback, false);
    }-*/;

    public native static void registerTransitionEndHandler(
            final Element pElement,
            final ITransitionEndEventHandler pHandler) /*-{
        var callback = function(){
            pHandler.@sk.mrtn.library.client.utils.CssAnimation.ITransitionEndEventHandler::onTransitionEnd()();
            pElement.removeEventListener(@sk.mrtn.library.client.utils.CssAnimation::TRANSITION_END, callback, false);
        }
        pElement.addEventListener(@sk.mrtn.library.client.utils.CssAnimation::TRANSITION_END, callback, false);
    }-*/;

}