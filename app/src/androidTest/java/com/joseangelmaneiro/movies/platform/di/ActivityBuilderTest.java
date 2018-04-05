package com.joseangelmaneiro.movies.platform.di;

import com.joseangelmaneiro.movies.platform.di.detail.DetailActivityModuleTest;
import com.joseangelmaneiro.movies.platform.di.list.ListActivityModuleTest;
import com.joseangelmaneiro.movies.platform.views.DetailMovieActivity;
import com.joseangelmaneiro.movies.platform.views.MovieListActivity;
import dagger.Module;
import dagger.android.ContributesAndroidInjector;


@Module
public abstract class ActivityBuilderTest {

    @ContributesAndroidInjector(modules = ListActivityModuleTest.class)
    abstract MovieListActivity bindListActivity();

    @ContributesAndroidInjector(modules = DetailActivityModuleTest.class)
    abstract DetailMovieActivity bindDetailActivity();

}