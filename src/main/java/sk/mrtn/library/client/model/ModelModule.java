package sk.mrtn.library.client.model;

import dagger.Module;
import dagger.Provides;

import javax.inject.Named;
import javax.inject.Singleton;

/**
 * @author klaun with courtesy of fishi
 */
@Module
public class ModelModule {

    @Provides
    IModel provideModel(Model model) {
        return model;
    }

    @Provides
    @Singleton
    @Named("Root")
    IModel provideRootModel(Model model) {
        return model;
    }
}
