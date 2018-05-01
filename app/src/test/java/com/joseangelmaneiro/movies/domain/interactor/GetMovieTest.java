package com.joseangelmaneiro.movies.domain.interactor;

import com.joseangelmaneiro.movies.domain.Movie;
import com.joseangelmaneiro.movies.domain.MoviesRepository;
import com.joseangelmaneiro.movies.domain.executor.JobScheduler;
import com.joseangelmaneiro.movies.domain.executor.UIScheduler;
import com.joseangelmaneiro.movies.utils.TestUtils;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import io.reactivex.observers.TestObserver;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;


public class GetMovieTest {

    private static final int MOVIEID = 1234;

    @Mock
    MoviesRepository repository;
    @Mock
    UIScheduler uiScheduler;
    @Mock
    JobScheduler jobScheduler;

    TestObserver<Movie> testObserver;

    GetMovie sut;


    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        sut = new GetMovie(repository, uiScheduler, jobScheduler);

        testObserver = new TestObserver<>();
    }

    @Test
    public void useCaseInvokesTheRepositoryAndReturnsAMovie() throws Exception {
        Movie movie = givenAMovieFromRepository();

        sut.buildUseCaseObservable(new GetMovie.Params(MOVIEID)).subscribe(testObserver);

        testObserver.assertValue(movie);
    }


    private Movie givenAMovieFromRepository() throws Exception {
        Movie movie = TestUtils.createMainMovie();
        when(repository.getMovie(eq(MOVIEID))).thenReturn(movie);
        return movie;
    }

}
