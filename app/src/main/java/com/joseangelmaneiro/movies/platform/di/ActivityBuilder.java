package com.joseangelmaneiro.movies.platform.di;

import com.joseangelmaneiro.movies.platform.di.detail.DetailActivityModule;
import com.joseangelmaneiro.movies.platform.di.list.ListActivityModule;
import com.joseangelmaneiro.movies.platform.views.DetailMovieActivity;
import com.joseangelmaneiro.movies.platform.views.MovieListActivity;
import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ActivityBuilder {

    @ContributesAndroidInjector(modules = ListActivityModule.class)
    abstract MovieListActivity bindListActivity();

    @ContributesAndroidInjector(modules = DetailActivityModule.class)
    abstract DetailMovieActivity bindDetailActivity();

}
