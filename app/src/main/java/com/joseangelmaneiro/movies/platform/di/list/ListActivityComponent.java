package com.joseangelmaneiro.movies.platform.di.list;

import com.joseangelmaneiro.movies.platform.views.MovieListActivity;
import dagger.Subcomponent;
import dagger.android.AndroidInjector;


@Subcomponent(modules = ListActivityModule.class)
public interface ListActivityComponent extends AndroidInjector<MovieListActivity>{

    @Subcomponent.Builder
    abstract class Builder extends AndroidInjector.Builder<MovieListActivity>{}

}
