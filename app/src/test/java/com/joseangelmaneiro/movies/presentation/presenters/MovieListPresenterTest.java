package com.joseangelmaneiro.movies.presentation.presenters;

import com.joseangelmaneiro.movies.domain.Movie;
import com.joseangelmaneiro.movies.domain.Observer;
import com.joseangelmaneiro.movies.domain.interactor.GetMovies;
import com.joseangelmaneiro.movies.domain.interactor.UseCase;
import com.joseangelmaneiro.movies.domain.interactor.UseCaseFactory;
import com.joseangelmaneiro.movies.presentation.MovieCellView;
import com.joseangelmaneiro.movies.presentation.MovieListView;
import com.joseangelmaneiro.movies.utils.TestUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.util.List;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


public class MovieListPresenterTest {

    private static final int POSITION = 0;

    @Mock UseCaseFactory useCaseFactory;
    @Mock UseCase useCase;
    @Mock MovieListView view;
    @Mock MovieCellView cellView;

    @Captor ArgumentCaptor<GetMovies.Params> paramsCaptor;
    @Captor ArgumentCaptor<Observer<List<Movie>>> observerCaptor;

    MovieListPresenter sut;


    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        sut = new MovieListPresenter(useCaseFactory);
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

        verify(useCase).execute(any(Observer.class), paramsCaptor.capture());
        assertThat(paramsCaptor.getValue().isOnlyOnline(), is(false));
    }

    @Test
    public void refresh_InvokesUseCase(){
        sut.refresh();

        verify(useCase).execute(any(Observer.class), paramsCaptor.capture());
        assertThat(paramsCaptor.getValue().isOnlyOnline(), is(true));
    }

    @Test
    public void saveResultAndRefreshViewWhenUseCaseReturnsAMovieList(){
        whenUseCaseReturnsAMovieList();

        assertFalse(sut.moviesListIsEmpty());
        verify(view).cancelRefreshDialog();
        verify(view).refreshList();
    }

    @Test
    public void showErrorMessageWhenUseCaseFiresAException(){
        Exception exception = whenUseCaseFiresAException();

        verify(view).cancelRefreshDialog();
        verify(view).showErrorMessage(eq(exception.getMessage()));
    }

    @Test
    public void getItemsCount_ReturnsZeroWhenThereIsNoData(){
        assertEquals(0, sut.getItemsCount());
    }

    @Test
    public void getItemsCount_ReturnsTheNumberOfItems(){
        List<Movie> movieList = givenAMovieList();

        assertThat(sut.getItemsCount(), is(movieList.size()));
    }

    @Test
    public void configureCell_DisplaysImage(){
        Movie movie = givenAMovieFromList();

        sut.configureCell(cellView, POSITION);

        verify(cellView).displayImage(eq(movie.getPosterPath()));
    }

    @Test
    public void onItemClick_SavesSelectedMovieIdAndInvokesNavigateToDetailScreen(){
        Movie movie = givenAMovieFromList();

        sut.onItemClick(POSITION);

        assertThat(sut.getSelectedMovieId(), is(movie.getId()));
        verify(view).navigateToDetailScreen(eq(movie.getId()));
    }

    private void whenUseCaseReturnsAMovieList() {
        sut.viewReady();

        List<Movie> movieList = TestUtils.createMainMovieList();
        verify(useCase).execute(observerCaptor.capture(), any());
        observerCaptor.getValue().onSuccess(movieList);
    }

    private Exception whenUseCaseFiresAException() {
        sut.viewReady();

        Exception exception = new Exception("Fake Error");
        verify(useCase).execute(observerCaptor.capture(), any());
        observerCaptor.getValue().onError(exception);
        return exception;
    }

    private List<Movie> givenAMovieList(){
        List<Movie> movieList = TestUtils.createMainMovieList();
        sut.saveMovies(movieList);
        return movieList;
    }

    private Movie givenAMovieFromList(){
        return givenAMovieList().get(POSITION);
    }

}
