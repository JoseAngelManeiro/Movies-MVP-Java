package com.joseangelmaneiro.movies.platform.di.app;

import android.app.Application;
import com.joseangelmaneiro.movies.platform.MoviesAppTest;
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
public interface AppComponentTest extends AndroidInjector<MoviesAppTest> {

    @Override
    void inject(MoviesAppTest app);

    @Component.Builder
    interface Builder {
        @BindsInstance
        AppComponentTest.Builder application(Application application);
        AppComponentTest build();
    }

}