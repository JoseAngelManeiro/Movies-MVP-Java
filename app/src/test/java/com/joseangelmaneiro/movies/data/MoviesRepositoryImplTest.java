package com.joseangelmaneiro.movies.data;

import com.joseangelmaneiro.movies.data.entity.MovieEntity;
import com.joseangelmaneiro.movies.data.entity.mapper.EntityDataMapper;
import com.joseangelmaneiro.movies.data.source.local.MoviesLocalDataSource;
import com.joseangelmaneiro.movies.data.source.remote.MoviesRemoteDataSource;
import com.joseangelmaneiro.movies.domain.Handler;
import com.joseangelmaneiro.movies.domain.Movie;
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
import static org.mockito.Mockito.when;


public class MoviesRepositoryImplTest {

    private static final int MOVIE_ID = 1234;

    private MoviesRepositoryImpl sut;
    @Mock
    private MoviesLocalDataSource localDataSource;
    @Mock
    private MoviesRemoteDataSource remoteDataSource;
    @Mock
    private EntityDataMapper entityDataMapper;
    @Mock
    private Handler<List<Movie>> moviesHandler;
    @Mock
    private Handler<Movie> movieHandler;
    @Captor
    private ArgumentCaptor<Handler<List<MovieEntity>>> moviesHandlerCaptor;
    @Captor
    private ArgumentCaptor<Handler<MovieEntity>> movieHandlerCaptor;

    private List<MovieEntity> movieEntityList;
    private MovieEntity movieEntity;
    private List<Movie> movieList;
    private Movie movie;


    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        sut = MoviesRepositoryImpl.getInstance(localDataSource, remoteDataSource, entityDataMapper);

        movieEntityList = TestUtils.createMainMovieEntityList();
        movieEntity = TestUtils.createMainMovieEntity();
        movieList = TestUtils.createMainMovieList();
        movie = TestUtils.createMainMovie();

        when(entityDataMapper.transform(movieEntityList)).thenReturn(movieList);
        when(entityDataMapper.transform(movieEntity)).thenReturn(movie);
    }

    @After
    public void destroyRepositoryInstance() {
        MoviesRepositoryImpl.destroyInstance();
    }

    @Test
    public void getMovies_ReturnsAllMoviesFromRemoteDataSource() {
        // When calling getAll in the repository
        sut.getMovies(moviesHandler);
        // Make the remote data source return data
        setMoviesAvailable(movieEntityList);

        // First verify that all movies are deleted from local data source
        verify(localDataSource).deleteAll();
        // Verify that the data fetched from the remote data source was saved in local
        verify(localDataSource).save(eq(movieEntityList));
        // Verify the movies from the remote data source are returned
        verify(moviesHandler).handle(eq(movieList));
    }

    @Test
    public void getMovies_FiresErrorFromRemoteDataSource() {
        // When calling getAll in the repository
        sut.getMovies(moviesHandler);
        // Make the remote data source return error
        setMoviesError();

        // Verify that the error is returned
        verify(moviesHandler).error();
    }

    @Test
    public void getMovie_ReturnsMovieFromLocalDataSource() {
        // When calling getAll in the repository
        sut.getMovie(MOVIE_ID, movieHandler);
        // Make the local data source return data
        setMovieAvailable(movieEntity);

        // Verify the movie from the local data source are returned
        verify(movieHandler).handle(movie);
    }


    private void setMoviesError() {
        verify(remoteDataSource).getAll(moviesHandlerCaptor.capture());
        moviesHandlerCaptor.getValue().error();
    }

    private void setMoviesAvailable(List<MovieEntity> movieList) {
        verify(remoteDataSource).getAll(moviesHandlerCaptor.capture());
        moviesHandlerCaptor.getValue().handle(movieList);
    }

    private void setMovieAvailable(MovieEntity movie) {
        verify(localDataSource).get(eq(MOVIE_ID), movieHandlerCaptor.capture());
        movieHandlerCaptor.getValue().handle(movie);
    }

}