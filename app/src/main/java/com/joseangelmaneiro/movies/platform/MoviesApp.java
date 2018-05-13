package com.joseangelmaneiro.movies.platform;

import com.joseangelmaneiro.movies.platform.di.app.AppComponent;
import com.joseangelmaneiro.movies.platform.di.app.DaggerAppComponent;
import dagger.android.AndroidInjector;
import dagger.android.DaggerApplication;


public class MoviesApp extends DaggerApplication {

    @Override
    protected AndroidInjector<MoviesApp> applicationInjector() {
        AppComponent appComponent = DaggerAppComponent.builder().application(this).build();
        appComponent.inject(this);
        return appComponent;
    }

}
