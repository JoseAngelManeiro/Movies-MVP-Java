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
import static org.mockito.ArgumentMatchers.any;
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
    public void getMovies_ReturnsAllMoviesFromRemoteDataSource() {
        givenAValidListFromRemote();

        thenSaveInLocalAndReturnList();
    }

    @Test
    public void getMovies_FiresErrorFromRemoteDataSource() {
        givenAExceptionFromRemote();

        verify(moviesHandler).error(any(Exception.class));
    }

    @Test
    public void getMovie_ReturnsMovieFromLocalDataSource() {
        givenAValidElementFromLocal();

        verify(movieHandler).handle(movie);
    }


    private void givenAValidListFromRemote(){
        sut.getMovies(moviesHandler);
        setMoviesAvailable(movieEntityList);
    }

    private void thenSaveInLocalAndReturnList(){
        verify(localDataSource).deleteAll();
        verify(localDataSource).save(eq(movieEntityList));
        verify(moviesHandler).handle(eq(movieList));
    }

    private void givenAExceptionFromRemote(){
        sut.getMovies(moviesHandler);
        setMoviesError(new Exception());
    }

    private void givenAValidElementFromLocal(){
        when(localDataSource.get(eq(MOVIE_ID))).thenReturn(movieEntity);
        sut.getMovie(MOVIE_ID, movieHandler);
    }

    private void setMoviesError(Exception exception) {
        ArgumentCaptor<Handler<List<MovieEntity>>> argumentCaptor = ArgumentCaptor.forClass(Handler.class);
        verify(remoteDataSource).getAll(argumentCaptor.capture());
        argumentCaptor.getValue().error(exception);
    }

    private void setMoviesAvailable(List<MovieEntity> movieEntityList) {
        verify(remoteDataSource).getAll(moviesHandlerCaptor.capture());
        moviesHandlerCaptor.getValue().handle(movieEntityList);
    }

}