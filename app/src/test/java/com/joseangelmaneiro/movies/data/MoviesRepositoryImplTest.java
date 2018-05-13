package com.joseangelmaneiro.movies.data;

import com.joseangelmaneiro.movies.data.entity.MovieEntity;
import com.joseangelmaneiro.movies.data.entity.mapper.EntityDataMapper;
import com.joseangelmaneiro.movies.data.source.local.MoviesLocalDataSource;
import com.joseangelmaneiro.movies.data.source.remote.MoviesRemoteDataSource;
import com.joseangelmaneiro.movies.domain.Movie;
import com.joseangelmaneiro.movies.utils.TestUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.util.Collections;
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

    private List<MovieEntity> movieEntityList;
    private MovieEntity movieEntity;
    private List<Movie> movieList;
    private Movie movie;


    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        sut = new MoviesRepositoryImpl(localDataSource, remoteDataSource, entityDataMapper);

        movieEntityList = TestUtils.createMainMovieEntityList();
        movieEntity = TestUtils.createMainMovieEntity();
        movieList = TestUtils.createMainMovieList();
        movie = TestUtils.createMainMovie();

        when(entityDataMapper.transform(movieEntityList)).thenReturn(movieList);
        when(entityDataMapper.transform(movieEntity)).thenReturn(movie);
    }

    @After
    public void destroyRepositoryInstance() {
        sut = null;
        movieEntityList = null;
        movieEntity = null;
        movieList = null;
        movie = null;
    }

    @Test
    public void getMovies_ReturnsAllMoviesFromRemoteDataSource() throws Exception {
        givenAValidListFromRemote();

        sut.getMovies(true);

        thenSaveInLocalAndTransformList();
    }

    @Test
    public void getMovies_ReturnsAllMoviesFromLocalDataSource() throws Exception {
        givenAValidListFromLocal();

        sut.getMovies(false);

        thenTransformList();
    }

    @Test
    public void getMovies_ReturnsEmptyListFromLocalDataSource() throws Exception {
        givenAEmptyListFromLocal();
        givenAValidListFromRemote();

        sut.getMovies(false);

        thenSaveInLocalAndTransformList();
    }

    @Test
    public void getMovie_ReturnsMovieFromLocalDataSource() {
        givenAValidMovieFromLocal();

        sut.getMovie(MOVIE_ID);

        thenTransformMovie();
    }

    private void givenAValidListFromRemote() throws Exception {
        when(remoteDataSource.getAll()).thenReturn(movieEntityList);
    }

    private void givenAEmptyListFromLocal() throws Exception {
        when(localDataSource.getAll()).thenReturn(Collections.emptyList());
    }

    private void givenAValidListFromLocal() throws Exception {
        when(localDataSource.getAll()).thenReturn(movieEntityList);
    }

    private void thenSaveInLocalAndTransformList(){
        verify(localDataSource).deleteAll();
        verify(localDataSource).save(eq(movieEntityList));
        thenTransformList();
    }

    private void thenTransformList(){
        verify(entityDataMapper).transform(eq(movieEntityList));
    }

    private void givenAValidMovieFromLocal(){
        when(localDataSource.get(eq(MOVIE_ID))).thenReturn(movieEntity);
    }

    private void thenTransformMovie(){
        verify(entityDataMapper).transform(eq(movieEntity));
    }

}