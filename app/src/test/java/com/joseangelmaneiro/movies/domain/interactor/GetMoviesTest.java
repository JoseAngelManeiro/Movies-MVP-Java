package com.joseangelmaneiro.movies.domain.interactor;

import com.joseangelmaneiro.movies.domain.Movie;
import com.joseangelmaneiro.movies.domain.MoviesRepository;
import com.joseangelmaneiro.movies.domain.executor.JobScheduler;
import com.joseangelmaneiro.movies.domain.executor.UIScheduler;
import com.joseangelmaneiro.movies.utils.TestUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.util.List;
import io.reactivex.observers.TestObserver;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;


public class GetMoviesTest {

    private static final boolean ONLYONLINE = true;

    @Mock
    MoviesRepository repository;
    @Mock
    UIScheduler uiScheduler;
    @Mock
    JobScheduler jobScheduler;

    TestObserver<List<Movie>> testObserver;

    GetMovies sut;


    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        sut = new GetMovies(repository, uiScheduler, jobScheduler);

        testObserver = new TestObserver<>();
    }

    @After
    public void tearDown() throws Exception {
        sut = null;
        testObserver = null;
    }

    @Test
    public void useCaseInvokesTheRepositoryAndReturnsAListOfMovies() throws Exception {
        List<Movie> movieList = givenAMovieListFromRepository();

        sut.buildUseCaseObservable(new GetMovies.Params(ONLYONLINE)).subscribe(testObserver);

        testObserver.assertValue(movieList);
    }

    @Test
    public void useCaseInvokesTheRepositoryAndFiresAException() throws Exception {
        givenAExceptionFromRepository();

        sut.buildUseCaseObservable(new GetMovies.Params(ONLYONLINE)).subscribe(testObserver);

        testObserver.assertError(Exception.class);
    }


    private List<Movie> givenAMovieListFromRepository() throws Exception {
        List<Movie> movieList = TestUtils.createMainMovieList();
        when(repository.getMovies(eq(ONLYONLINE))).thenReturn(movieList);
        return movieList;
    }

    private void givenAExceptionFromRepository() throws Exception {
        when(repository.getMovies(eq(ONLYONLINE))).thenThrow(new Exception());
    }

}
