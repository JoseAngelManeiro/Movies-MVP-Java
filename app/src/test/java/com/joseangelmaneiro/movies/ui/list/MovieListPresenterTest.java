package com.joseangelmaneiro.movies.ui.list;

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
import java.util.List;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


public class MovieListPresenterTest {

    private final List<Movie> movieList = TestUtils.createMainMovieList();
    private final List<MovieViewModel> movieViewModelList = TestUtils.createMainMovieViewModelList();

    private MovieListPresenter sut;
    @Mock
    private MoviesRepository repository;
    @Mock
    private MovieMapper movieMapper;
    @Mock
    private MovieListView view;
    @Captor
    private ArgumentCaptor<Handler<List<Movie>>> moviesHandlerCaptor;


    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        sut = new MovieListPresenter(repository, movieMapper);
        sut.setView(view);

        when(movieMapper.transform(movieList)).thenReturn(movieViewModelList);
    }

    @After
    public void tearDown() {
        sut = null;
    }

    @Test
    public void viewReady_InvokesGetMovies(){
        sut.viewReady();

        verify(repository).getMovies(any(Handler.class));
    }

    @Test
    public void refresh_InvokesGetMovies(){
        sut.refresh();

        verify(repository).getMovies(any(Handler.class));
    }

    @Test
    public void invokeGetMovies_RefreshesView(){
        sut.invokeGetMovies();
        setMoviesAvailable(movieList);

        verify(view).cancelRefreshDialog();
        verify(view).refreshList(movieViewModelList);
    }

    @Test
    public void invokeGetMovies_ShowsError(){
        sut.invokeGetMovies();
        setMoviesError();

        verify(view).cancelRefreshDialog();
        verify(view).showErrorMessage();
    }

    @Test
    public void onItemClick_InvokesNavigateToDetailScreen(){
        MovieViewModel movieViewModel = TestUtils.createMainMovieViewModel();

        sut.onItemClick(movieViewModel);

        verify(view).navigateToDetailScreen(eq(movieViewModel.getId()));
    }


    private void setMoviesAvailable(List<Movie> movieList) {
        verify(repository).getMovies(moviesHandlerCaptor.capture());
        moviesHandlerCaptor.getValue().handle(movieList);
    }

    private void setMoviesError() {
        verify(repository).getMovies(moviesHandlerCaptor.capture());
        moviesHandlerCaptor.getValue().error();
    }

}