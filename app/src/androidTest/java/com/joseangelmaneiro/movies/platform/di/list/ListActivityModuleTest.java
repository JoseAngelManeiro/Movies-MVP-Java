package com.joseangelmaneiro.movies.platform.di.list;

import com.joseangelmaneiro.movies.presentation.presenters.MovieListPresenter;
import org.mockito.Mockito;
import dagger.Module;
import dagger.Provides;


@Module
public class ListActivityModuleTest {

    @Provides
    MovieListPresenter providePresenter(){
        return Mockito.mock(MovieListPresenter.class);
    }

}