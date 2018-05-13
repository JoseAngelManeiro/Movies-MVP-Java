package com.joseangelmaneiro.movies.domain.interactor;

import com.joseangelmaneiro.movies.domain.Handler;
import com.joseangelmaneiro.movies.domain.Movie;
import com.joseangelmaneiro.movies.domain.MoviesRepository;
import com.joseangelmaneiro.movies.utils.TestUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.util.List;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;


public class GetMoviesTest {

    @Mock
    MoviesRepository repository;
    @Mock
    Handler<List<Movie>> handler;
    @Captor
    ArgumentCaptor<Handler<List<Movie>>> movieListCaptor;

    GetMovies sut;


    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        sut = new GetMovies(repository);
    }

    @After
    public void tearDown() throws Exception {
        sut = null;
    }

    @Test
    public void execute_InvokesRepository(){
        sut.execute(null, null);

        verify(repository).getMovies(any(Handler.class));
    }

    @Test
    public void execute_ReturnsMovieList(){
        List<Movie> movieList = TestUtils.createMainMovieList();

        sut.execute(handler, null);
        setMovieListAvailable(movieList);

        verify(handler).handle(eq(movieList));
    }

    @Test
    public void execute_FiresError(){
        sut.execute(handler, null);
        setError(new Exception());

        verify(handler).error(any(Exception.class));
    }


    private void setMovieListAvailable(List<Movie> movieList){
        verify(repository).getMovies(movieListCaptor.capture());
        movieListCaptor.getValue().handle(movieList);
    }

    private void setError(Exception exception){
        verify(repository).getMovies(movieListCaptor.capture());
        movieListCaptor.getValue().error(exception);
    }

}