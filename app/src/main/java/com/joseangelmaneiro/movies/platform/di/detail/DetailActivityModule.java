package com.joseangelmaneiro.movies.platform.di.detail;

import com.joseangelmaneiro.movies.domain.interactor.UseCaseFactory;
import com.joseangelmaneiro.movies.platform.views.DetailMovieActivity;
import com.joseangelmaneiro.movies.presentation.formatters.Formatter;
import com.joseangelmaneiro.movies.presentation.presenters.DetailMoviePresenter;
import dagger.Module;
import dagger.Provides;


@Module
public class DetailActivityModule {

    @Provides
    DetailMoviePresenter providePresenter(DetailMovieActivity activity, UseCaseFactory useCaseFactory,
                                          Formatter formatter){
        int movieId = activity.getIntent().getIntExtra(DetailMovieActivity.EXTRA_MOVIE_ID, -1);
        DetailMoviePresenter presenter = new DetailMoviePresenter(useCaseFactory, formatter, movieId);
        presenter.setView(activity);
        return presenter;
    }

}
