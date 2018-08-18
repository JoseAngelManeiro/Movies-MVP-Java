package com.joseangelmaneiro.movies.ui.detail;

import com.joseangelmaneiro.movies.data.Handler;
import com.joseangelmaneiro.movies.data.Movie;
import com.joseangelmaneiro.movies.data.MoviesRepository;
import com.joseangelmaneiro.movies.ui.MovieMapper;
import com.joseangelmaneiro.movies.ui.MovieViewModel;
import com.joseangelmaneiro.movies.utils.TestUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


public class DetailMoviePresenterTest {

    private static final int MOVIE_ID = 1234;

    private final Movie movie = TestUtils.createMainMovie();
    private final MovieViewModel movieViewModel = TestUtils.createMainMovieViewModel();

    DetailMoviePresenter sut;
    @Mock
    MoviesRepository repository;
    @Mock
    MovieMapper movieMapper;
    @Mock
    DetailMovieView view;
    @Captor
    private ArgumentCaptor<Handler<Movie>> movieHandlerCaptor;


    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        sut = new DetailMoviePresenter(repository, movieMapper, MOVIE_ID);
        sut.setView(view);

        when(movieMapper.transform(movie)).thenReturn(movieViewModel);
    }

    @After
    public void tearDown() {
        sut = null;
    }

    @Test
    public void viewReady_InvokesGetMovie(){
        sut.viewReady();

        verify(repository).getMovie(eq(MOVIE_ID), any(Handler.class));
    }

    @Test
    public void viewReady_DisplaysTheView(){
        sut.viewReady();
        setMovieAvailable(movie);

        verify(view).displayImage(eq(movieViewModel.getBackdropPath()));
        verify(view).displayTitle(eq(movieViewModel.getTitle()));
        verify(view).displayVoteAverage(eq(movieViewModel.getVoteAverage()));
        verify(view).displayReleaseDate(eq(movieViewModel.getReleaseDate()));
        verify(view).displayOverview(eq(movieViewModel.getOverview()));
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