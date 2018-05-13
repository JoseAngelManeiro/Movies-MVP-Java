package com.joseangelmaneiro.movies.presentation.presenters;

import com.joseangelmaneiro.movies.domain.Handler;
import com.joseangelmaneiro.movies.domain.Movie;
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
import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


public class DetailMoviePresenterTest {

    private static final int MOVIE_ID = 1234;

    DetailMoviePresenter sut;
    @Mock
    UseCaseFactory useCaseFactory;
    @Mock
    UseCase useCase;
    @Mock
    Formatter formatter;
    @Mock
    DetailMovieView view;
    @Captor
    private ArgumentCaptor<Handler<Movie>> movieHandlerCaptor;
    @Captor
    private ArgumentCaptor<String> textCaptor;


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
        ArgumentCaptor<GetMovie.Params> paramsArgumentCaptor = ArgumentCaptor
                .forClass(GetMovie.Params.class);

        sut.viewReady();

        verify(useCase).execute(any(Handler.class), paramsArgumentCaptor.capture());
        assertEquals(MOVIE_ID, paramsArgumentCaptor.getValue().getMovieId());
    }

    @Test
    public void viewReady_InvokesDisplayImage(){
        Movie movie = TestUtils.createMainMovie();

        sut.viewReady();
        setMovieAvailable(movie);

        verify(view).displayImage(eq(movie.getBackdropPath()));
    }

    @Test
    public void viewReady_InvokesDisplayTitle(){
        Movie movie = TestUtils.createMainMovie();
        String titleExpected = movie.getTitle();

        sut.viewReady();
        setMovieAvailable(movie);

        verify(view).displayTitle(textCaptor.capture());
        assertEquals(titleExpected, textCaptor.getValue());
    }

    @Test
    public void viewReady_InvokesDisplayVoteAverage(){
        Movie movie = TestUtils.createMainMovie();
        String voteAverageExpected = movie.getVoteAverage();

        sut.viewReady();
        setMovieAvailable(movie);

        verify(view).displayVoteAverage(textCaptor.capture());
        assertEquals(voteAverageExpected, textCaptor.getValue());
    }

    @Test
    public void viewReady_InvokesDisplayReleaseDate(){
        String fakeDate = "22/10/2017";
        when(formatter.formatDate(anyString())).thenReturn(fakeDate);

        sut.viewReady();
        setMovieAvailable(TestUtils.createMainMovie());

        verify(view).displayReleaseDate(textCaptor.capture());
        assertEquals(fakeDate, textCaptor.getValue());
    }

    @Test
    public void viewReady_InvokesDisplayOverview(){
        Movie movie = TestUtils.createMainMovie();
        String overviewExpected = movie.getOverview();

        sut.viewReady();
        setMovieAvailable(movie);

        verify(view).displayOverview(textCaptor.capture());
        assertEquals(overviewExpected, textCaptor.getValue());
    }

    @Test
    public void navUpSelected_InvokesGoToBack(){
        sut.navUpSelected();

        verify(view).goToBack();
    }


    private void setMovieAvailable(Movie movie) {
        verify(useCase).execute(movieHandlerCaptor.capture(), any());
        movieHandlerCaptor.getValue().handle(movie);
    }

}