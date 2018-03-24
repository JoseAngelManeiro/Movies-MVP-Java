package com.joseangelmaneiro.movies.platform.di.detail;

import com.joseangelmaneiro.movies.platform.views.DetailMovieActivity;
import dagger.Subcomponent;
import dagger.android.AndroidInjector;


@Subcomponent(modules = DetailActivityModule.class)
public interface DetailActivityComponent extends AndroidInjector<DetailMovieActivity>{

    @Subcomponent.Builder
    abstract class Builder extends AndroidInjector.Builder<DetailMovieActivity>{}

}
