package com.joseangelmaneiro.movies.presentation.presenters;

import com.joseangelmaneiro.movies.domain.Movie;
import com.joseangelmaneiro.movies.domain.Observer;
import com.joseangelmaneiro.movies.domain.interactor.GetMovie;
import com.joseangelmaneiro.movies.domain.interactor.UseCase;
import com.joseangelmaneiro.movies.domain.interactor.UseCaseFactory;
import com.joseangelmaneiro.movies.presentation.DetailMovieView;
import com.joseangelmaneiro.movies.presentation.formatters.Formatter;
import com.joseangelmaneiro.movies.utils.TestUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


public class DetailMoviePresenterTest {

    private static final int MOVIE_ID = 1234;
    private static final String RELEASE_DATE = "22/10/2017";

    @Mock UseCaseFactory useCaseFactory;
    @Mock UseCase useCase;
    @Mock Formatter formatter;
    @Mock DetailMovieView view;

    @Captor ArgumentCaptor<GetMovie.Params> paramsCaptor;
    @Captor ArgumentCaptor<Observer<Movie>> observerCaptor;

    DetailMoviePresenter sut;


    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        sut = new DetailMoviePresenter(useCaseFactory, formatter, MOVIE_ID);
        sut.setView(view);

        when(useCaseFactory.getMovie()).thenReturn(useCase);
    }

    @After
    public void tearDown() throws Exception {
        sut = null;
    }

    @Test
    public void viewReady_InvokesUseCase(){
        sut.viewReady();

        verify(useCase).execute(any(Observer.class), paramsCaptor.capture());
        assertThat(paramsCaptor.getValue().getMovieId(), is(MOVIE_ID));
    }

    @Test
    public void displayValuesWhenUseReturnsAMovie(){
        Movie movie = whenUseCaseReturnsAMovie();

        thenDisplayMovieValues(movie);
    }

    private void thenDisplayMovieValues(Movie movie){
        verify(view).displayImage(eq(movie.getBackdropPath()));
        verify(view).displayTitle(eq(movie.getTitle()));
        verify(view).displayVoteAverage(eq(movie.getVoteAverage()));
        verify(view).displayReleaseDate(eq(RELEASE_DATE));
        verify(view).displayOverview(eq(movie.getOverview()));
    }

    @Test
    public void navUpSelected_InvokesGoToBack(){
        sut.navUpSelected();

        verify(view).goToBack();
    }

    private Movie whenUseCaseReturnsAMovie() {
        when(formatter.formatDate(anyString())).thenReturn(RELEASE_DATE);

        sut.viewReady();

        Movie movie = TestUtils.createMainMovie();
        verify(useCase).execute(observerCaptor.capture(), any());
        observerCaptor.getValue().onSuccess(movie);
        return movie;
    }

}
