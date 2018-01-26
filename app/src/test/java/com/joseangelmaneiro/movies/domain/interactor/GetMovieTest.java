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

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;


public class GetMovieTest {

    @Mock
    MoviesRepository repository;
    @Mock
    Handler<Movie> handler;
    @Captor
    ArgumentCaptor<Handler<Movie>> movieCaptor;

    GetMovie sut;

    int fakeMovieId = 1234;


    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        sut = new GetMovie(repository);
    }

    @After
    public void tearDown() throws Exception {
        sut = null;
    }

    @Test
    public void execute_InvokesRepository(){
        sut.execute(null, new GetMovie.Params(fakeMovieId));

        verify(repository).getMovie(eq(fakeMovieId), any(Handler.class));
    }

    @Test
    public void execute_ReturnsMovie(){
        Movie movie = TestUtils.createMainMovie();

        sut.execute(handler, new GetMovie.Params(fakeMovieId));
        setMovieAvailable(movie);

        verify(handler).handle(eq(movie));
    }

    @Test
    public void execute_FiresError(){
        sut.execute(handler, new GetMovie.Params(fakeMovieId));
        setError();

        verify(handler).error();
    }


    private void setMovieAvailable(Movie movie){
        verify(repository).getMovie(eq(fakeMovieId), movieCaptor.capture());
        movieCaptor.getValue().handle(movie);
    }

    private void setError(){
        verify(repository).getMovie(eq(fakeMovieId), movieCaptor.capture());
        movieCaptor.getValue().error();
    }

}