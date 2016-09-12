package sk.mrtn.library.client.develop.console.screenconsole;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.CssResource;

/**
 * Created by martinliptak on 02/09/15.
 */
interface ScreenConsoleDisplayResources extends ClientBundle {

    ScreenConsoleDisplayResources impl = GWT
            .create(ScreenConsoleDisplayResources.class);

    @Source("screenConsoleDisplayStyle.css")
    ScreenConsoleDisplayResource screenConsoleDisplayStyle();

    interface ScreenConsoleDisplayResource extends CssResource {

        @ClassName("debug")
        String debug();

        @ClassName("info")
        String info();

        @ClassName("warning")
        String warning();

        @ClassName("error")
        String error();

        @ClassName("background")
        String background();

        // anotacie su potrebne len ak sa inak volaju metody a classes
        @ClassName("minimized")
        String minimized();

        @ClassName("match-parent")
        String matchparent();

        @ClassName("button")
        String button();

        @ClassName("resizeButton")
        String resizeButton();

        @ClassName("closeButton")
        String closeButton();

        @ClassName("hideButton")
        String hideButton();

        @ClassName("messagesContainer")
        String messagesContainer();

        @ClassName("hidden")
        String hidden();

    }

}

