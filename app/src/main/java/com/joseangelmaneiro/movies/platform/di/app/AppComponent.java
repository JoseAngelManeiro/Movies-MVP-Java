package com.joseangelmaneiro.movies.platform.di.app;

import android.app.Application;
import com.joseangelmaneiro.movies.platform.MoviesApp;
import com.joseangelmaneiro.movies.platform.di.ActivityBuilder;
import javax.inject.Singleton;
import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjector;
import dagger.android.support.AndroidSupportInjectionModule;

@Singleton
@Component(modules = {
        AndroidSupportInjectionModule.class,
        AppModule.class,
        ActivityBuilder.class})
public interface AppComponent extends AndroidInjector<MoviesApp> {

    @Override
    void inject(MoviesApp app);

    @Component.Builder
    interface Builder {
        /* This method is necessary for DaggerMock when running instrumentations tests */
        Builder appModule(AppModule appModule);

        @BindsInstance
        Builder application(Application application);
        AppComponent build();
    }

}
