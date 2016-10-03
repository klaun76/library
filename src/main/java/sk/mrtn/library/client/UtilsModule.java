package sk.mrtn.library.client;

import com.google.gwt.event.shared.EventBus;
import com.google.gwt.event.shared.ResettableEventBus;
import com.google.gwt.event.shared.SimpleEventBus;
import com.google.gwt.user.client.Window;
import dagger.Module;
import dagger.Provides;
import sk.mrtn.library.client.communication.HttpRequest;
import sk.mrtn.library.client.communication.IHttpRequest;
import sk.mrtn.library.client.ui.mainpanel.IRootResponsivePanel;
import sk.mrtn.library.client.ui.mainpanel.RootResponsivePanel;
import sk.mrtn.library.client.ui.togglebutton.IToggleButton;
import sk.mrtn.library.client.ui.togglebutton.ToggleButton;
import sk.mrtn.library.client.utils.IUrlParametersManager;
import sk.mrtn.library.client.utils.UrlParametersManager;
import sk.mrtn.library.client.utils.mobiledetect.MobileDetect;
import sk.mrtn.library.client.utils.orientationchange.IWindowStateChangeHandler;
import sk.mrtn.library.client.utils.orientationchange.WindowStateChangeHandler;
import sk.mrtn.library.client.window.IWindowStateController;
import sk.mrtn.library.client.window.WindowStateController;

import javax.inject.Named;
import javax.inject.Singleton;

/**
 * Created by martinliptak on 24/04/16.
 */
@Module()
public class UtilsModule {

    @Provides @Singleton
    IWindowStateController provideWindowController(WindowStateController controller) {
        return controller;
    }

    @Provides @Singleton
    IUrlParametersManager provideIUrlParametersManager() {
        return new UrlParametersManager();
    }

    @Provides @Singleton
    MobileDetect provideMobileDetect() {
        return new MobileDetect(Window.Navigator.getUserAgent());
    }

    @Provides
    IHttpRequest provideILoader (HttpRequest loader) {
        return loader;
    }

    @Provides
    @Singleton
    @Named("Common")
    EventBus provideCommonEventBus() {
        return new ResettableEventBus(new SimpleEventBus());
    }

    @Provides
    @Singleton
    IWindowStateChangeHandler provideWindowStateChangeHandler(
            WindowStateChangeHandler windowStateChangeHandler
    ){
        return windowStateChangeHandler;
    }

    @Provides
    IToggleButton provideIToggleButton(ToggleButton button) {
        return button;
    }

    @Provides
    @Singleton
    IRootResponsivePanel provideIMainResponsivePanel(RootResponsivePanel mainResponsivePanel){
        return mainResponsivePanel;
    }
}
