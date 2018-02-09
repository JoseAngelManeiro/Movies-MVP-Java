package com.joseangelmaneiro.movies.presentation.presenters;

import com.joseangelmaneiro.movies.domain.Handler;
import com.joseangelmaneiro.movies.domain.Movie;
import com.joseangelmaneiro.movies.domain.interactor.UseCase;
import com.joseangelmaneiro.movies.domain.interactor.UseCaseFactory;
import com.joseangelmaneiro.movies.presentation.MovieCellView;
import com.joseangelmaneiro.movies.presentation.MovieListView;
import com.joseangelmaneiro.movies.presentation.formatters.Formatter;
import com.joseangelmaneiro.movies.utils.TestUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.ArgumentMatchers;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.util.List;
import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.isNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


public class MovieListPresenterTest {

    private static final String URL_TO_DISPLAY = "fake_url";

    private MovieListPresenter sut;
    @Mock
    UseCaseFactory useCaseFactory;
    @Mock
    UseCase useCase;
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

        sut = new MovieListPresenter(useCaseFactory, formatter);
        sut.setView(view);

        when(useCaseFactory.getMovies()).thenReturn(useCase);
    }

    @After
    public void tearDown() throws Exception {
        sut = null;
    }

    @Test
    public void viewReady_InvokesUseCase(){
        sut.viewReady();

        verify(useCase).execute(any(Handler.class), isNull());
    }

    @Test
    public void refresh_InvokesUseCase(){
        sut.refresh();

        verify(useCase).execute(any(Handler.class), isNull());
    }

    @Test
    public void viewReady_SavesMovies(){
        List<Movie> fakeMovieList = TestUtils.createMainMovieList();

        sut.viewReady();
        setMoviesAvailable(fakeMovieList);

        // If repository returns movies, they are saved
        assertFalse(sut.moviesListIsEmpty());
    }

    @Test
    public void viewReady_RefreshesViewCorrectly(){
        List<Movie> fakeMovieList = TestUtils.createMainMovieList();

        sut.viewReady();
        setMoviesAvailable(fakeMovieList);

        verify(view).cancelRefreshDialog();
        verify(view).refreshList();
    }

    @Test
    public void viewReady_FiresErrorMessage(){
        sut.viewReady();
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
        List<Movie> fakeMovieList = TestUtils.createMovieList(itemsExpected);

        sut.saveMovies(fakeMovieList);

        assertEquals(itemsExpected, sut.getItemsCount());
    }

    @Test
    public void configureCell_DisplaysImage(){
        when(formatter.getCompleteUrlImage(anyString())).thenReturn(URL_TO_DISPLAY);
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
        verify(useCase).execute(moviesHandlerCaptor.capture(), isNull());
        moviesHandlerCaptor.getValue().handle(movieList);
    }

    private void setMoviesError() {
        verify(useCase).execute(moviesHandlerCaptor.capture(), isNull());
        moviesHandlerCaptor.getValue().error();
    }

}