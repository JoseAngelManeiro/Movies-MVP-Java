package com.joseangelmaneiro.movies.platform;

import com.joseangelmaneiro.movies.platform.di.app.AppComponentTest;
import dagger.android.AndroidInjector;
import dagger.android.DaggerApplication;


public class MoviesAppTest extends DaggerApplication {

    @Override
    protected AndroidInjector<MoviesAppTest> applicationInjector() {
        AppComponentTest appComponent = DaggerAppComponentTest.builder().application(this).build();
        appComponent.inject(this);
        return appComponent;
    }

}