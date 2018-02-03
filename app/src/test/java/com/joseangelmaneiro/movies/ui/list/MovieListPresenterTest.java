package com.joseangelmaneiro.movies.ui.list;

import com.joseangelmaneiro.movies.domain.Handler;
import com.joseangelmaneiro.movies.domain.Movie;
import com.joseangelmaneiro.movies.domain.MoviesRepository;
import com.joseangelmaneiro.movies.presentation.MovieCellView;
import com.joseangelmaneiro.movies.presentation.presenters.MovieListPresenter;
import com.joseangelmaneiro.movies.presentation.MovieListView;
import com.joseangelmaneiro.movies.presentation.formatters.Formatter;
import com.joseangelmaneiro.movies.utils.TestUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.util.List;
import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


public class MovieListPresenterTest {


    private static final String URL_TO_DISPLAY = "fake_url";

    private MovieListPresenter sut;
    @Mock
    private MoviesRepository repository;
    @Mock
    private Formatter formatter;
    @Mock
    private MovieListView view;
    @Mock
    private MovieCellView cellView;
    @Captor
    private ArgumentCaptor<Handler<List<Movie>>> moviesHandlerCaptor;
    @Captor
    private ArgumentCaptor<String> textCaptor;
    @Captor
    private ArgumentCaptor<Integer> intCaptor;


    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        sut = new MovieListPresenter(repository, formatter);
        sut.setView(view);

        when(formatter.getCompleteUrlImage(anyString())).thenReturn(URL_TO_DISPLAY);
    }

    @After
    public void tearDown() throws Exception {
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
    public void invokeGetMovies_SavesMovies(){
        // The list is empty when starting
        assertTrue(sut.moviesListIsEmpty());

        sut.invokeGetMovies();

        setMoviesAvailable(TestUtils.createMainMovieList());

        // If repository returns movies, they are saved
        assertFalse(sut.moviesListIsEmpty());
    }

    @Test
    public void invokeGetMovies_RefreshesView(){
        sut.invokeGetMovies();

        setMoviesAvailable(TestUtils.createMainMovieList());

        verify(view).cancelRefreshDialog();
        verify(view).refreshList();
    }

    @Test
    public void invokeGetMovies_ShowsError(){
        sut.invokeGetMovies();

        setMoviesError();

        verify(view).cancelRefreshDialog();
        verify(view).showErrorMessage();
    }

    @Test
    public void getItemsCount_ReturnsZeroWhenThereIsNoData(){
        assertEquals(0, sut.getItemsCount());
    }

    @Test
    public void getItemsCount_ReturnsNumberOfItems(){
        int itemsExpected = 10;
        sut.saveMovies(TestUtils.createMovieList(itemsExpected));

        assertEquals(itemsExpected, sut.getItemsCount());
    }

    @Test
    public void configureCell_DisplaysImage(){
        sut.saveMovies(TestUtils.createMainMovieList());

        sut.configureCell(cellView, 1);

        verify(cellView).displayImage(textCaptor.capture());
        assertEquals(URL_TO_DISPLAY, textCaptor.getValue());
    }

    @Test
    public void onItemClick_SavesSelectedMovieId(){
        int fakePosition = 0;
        List<Movie> movieList = TestUtils.createMainMovieList();
        int idExpected = movieList.get(fakePosition).getId();
        sut.saveMovies(movieList);

        sut.onItemClick(fakePosition);

        assertEquals(idExpected, sut.getSelectedMovieId());
    }

    @Test
    public void onItemClick_InvokesNavigateToDetailScreen(){
        int fakePosition = 0;
        List<Movie> movieList = TestUtils.createMainMovieList();
        int idExpected = movieList.get(fakePosition).getId();
        sut.saveMovies(movieList);

        sut.onItemClick(fakePosition);

        verify(view).navigateToDetailScreen(intCaptor.capture());
        assertEquals(idExpected, intCaptor.getValue().intValue());
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