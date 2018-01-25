package com.joseangelmaneiro.movies.ui.detail;

import com.joseangelmaneiro.movies.domain.Handler;
import com.joseangelmaneiro.movies.domain.Movie;
import com.joseangelmaneiro.movies.domain.MoviesRepository;
import com.joseangelmaneiro.movies.ui.Formatter;
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
    MoviesRepository repository;
    @Mock
    Formatter formatter;
    @Mock
    DetailMovieView view;
    @Captor
    private ArgumentCaptor<Handler<Movie>> movieHandlerCaptor;
    @Captor
    private ArgumentCaptor<String> textCaptor;
    @Captor
    private ArgumentCaptor<Integer> intCaptor;


    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        sut = new DetailMoviePresenter(repository, formatter, MOVIE_ID);
        sut.setView(view);

    }

    @After
    public void tearDown() throws Exception {
        sut = null;
    }

    @Test
    public void viewReady_InvokesGetMovie(){
        sut.viewReady();

        verify(repository).getMovie(intCaptor.capture(), any(Handler.class));
        assertEquals(MOVIE_ID, intCaptor.getValue().intValue());
    }

    @Test
    public void viewReady_InvokesDisplayImage(){
        String fakePath = "fake-path";
        when(formatter.getCompleteUrlImage(anyString())).thenReturn(fakePath);
        sut.viewReady();
        setMovieAvailable(TestUtils.createMainMovie());

        verify(view).displayImage(textCaptor.capture());
        assertEquals(fakePath, textCaptor.getValue());
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
    public void viewReady_FiresErrorMessage(){
        sut.viewReady();
        setMoviesError();

        verify(view).showErrorMessage();
    }

    @Test
    public void navUpSelected_InvokesGoToBack(){
        sut.navUpSelected();

        verify(view).goToBack();
    }


    private void setMovieAvailable(Movie movie) {
        verify(repository).getMovie(eq(MOVIE_ID), movieHandlerCaptor.capture());
        movieHandlerCaptor.getValue().handle(movie);
    }

    private void setMoviesError() {
        verify(repository).getMovie(eq(MOVIE_ID), movieHandlerCaptor.capture());
        movieHandlerCaptor.getValue().error();
    }

}