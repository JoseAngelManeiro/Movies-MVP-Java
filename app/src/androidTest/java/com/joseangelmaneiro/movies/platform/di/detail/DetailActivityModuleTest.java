package com.joseangelmaneiro.movies.platform.di.detail;

import com.joseangelmaneiro.movies.presentation.presenters.DetailMoviePresenter;
import org.mockito.Mockito;
import dagger.Module;
import dagger.Provides;

@Module
public class DetailActivityModuleTest {

    @Provides
    DetailMoviePresenter providePresenter(){
        return Mockito.mock(DetailMoviePresenter.class);
    }

}