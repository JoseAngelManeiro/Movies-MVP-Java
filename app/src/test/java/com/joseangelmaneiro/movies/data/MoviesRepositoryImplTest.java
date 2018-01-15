package com.joseangelmaneiro.movies.data;

import com.joseangelmaneiro.movies.data.source.local.MoviesLocalDataSource;
import com.joseangelmaneiro.movies.data.source.remote.MoviesRemoteDataSource;
import com.joseangelmaneiro.movies.domain.Handler;
import com.joseangelmaneiro.movies.utils.TestUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.util.List;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.verify;


public class MoviesRepositoryImplTest {

    private static List<Movie> MOVIES = TestUtils.createMovieList(10);
    private static Movie MOVIE = TestUtils.createMainMovie();

    private MoviesRepositoryImpl sut;
    @Mock
    private MoviesLocalDataSource localDataSource;
    @Mock
    private MoviesRemoteDataSource remoteDataSource;
    @Mock
    private Handler<List<Movie>> moviesHandler;
    @Mock
    private Handler<Movie> movieHandler;
    @Captor
    private ArgumentCaptor<Handler<List<Movie>>> moviesHandlerCaptor;
    @Captor
    private ArgumentCaptor<Handler<Movie>> movieHandlerCaptor;


    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        sut = MoviesRepositoryImpl.getInstance(localDataSource, remoteDataSource);
    }

    @After
    public void destroyRepositoryInstance() {
        MoviesRepositoryImpl.destroyInstance();
    }

    @Test
    public void getMovies_ReturnsAllMoviesFromRemoteDataSource() {
        // When calling getMovies in the repository
        sut.getMovies(moviesHandler);

        // Make the remote data source return data
        setMoviesAvailable(MOVIES);

        // First verify that all movies are deleted from local data source
        verify(localDataSource).deleteAllMovies();

        // Verify that the data fetched from the remote data source was saved in local
        verify(localDataSource).saveMovies(MOVIES);

        // Verify the movies from the remote data source are returned
        verify(moviesHandler).handle(MOVIES);
    }

    @Test
    public void getMovies_FiresErrorFromRemoteDataSource() {
        // When calling getMovies in the repository
        sut.getMovies(moviesHandler);

        // Make the remote data source return error
        setMoviesError();

        // Verify that the error is returned
        verify(moviesHandler).error();
    }

    @Test
    public void getMovie_ReturnsMovieFromLocalDataSource() {
        // When calling getMovie in the repository
        sut.getMovie(MOVIE.getId(), movieHandler);

        // Make the local data source return data
        setMovieAvailable(MOVIE);

        // Verify the movie from the local data source are returned
        verify(movieHandler).handle(MOVIE);
    }

    @Test
    public void getMovie_FiresErrorFromLocalDataSource() {
        // When calling getMovie in the repository
        sut.getMovie(MOVIE.getId(), movieHandler);

        // Make the local data source return error
        setMovieError(MOVIE.getId());

        // Verify that the error is returned
        verify(movieHandler).error();
    }


    private void setMoviesError() {
        verify(remoteDataSource).getMovies(moviesHandlerCaptor.capture());
        moviesHandlerCaptor.getValue().error();
    }

    private void setMoviesAvailable(List<Movie> movieList) {
        verify(remoteDataSource).getMovies(moviesHandlerCaptor.capture());
        moviesHandlerCaptor.getValue().handle(movieList);
    }

    private void setMovieError(int movieId) {
        verify(localDataSource).getMovie(eq(movieId), movieHandlerCaptor.capture());
        movieHandlerCaptor.getValue().error();
    }

    private void setMovieAvailable(Movie movie) {
        verify(localDataSource).getMovie(eq(movie.getId()), movieHandlerCaptor.capture());
        movieHandlerCaptor.getValue().handle(movie);
    }

}