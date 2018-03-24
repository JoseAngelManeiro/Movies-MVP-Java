package com.joseangelmaneiro.movies.platform.di.list;

import com.joseangelmaneiro.movies.domain.interactor.UseCaseFactory;
import com.joseangelmaneiro.movies.platform.views.MovieListActivity;
import com.joseangelmaneiro.movies.presentation.presenters.MovieListPresenter;
import dagger.Module;
import dagger.Provides;

@Module
public class ListActivityModule {

    @Provides
    MovieListPresenter providePresenter(MovieListActivity activity, UseCaseFactory useCaseFactory){
        MovieListPresenter presenter = new MovieListPresenter(useCaseFactory);
        presenter.setView(activity);
        return presenter;
    }

}
