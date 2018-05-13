package com.joseangelmaneiro.movies.presentation.presenters;

import com.joseangelmaneiro.movies.domain.Handler;
import com.joseangelmaneiro.movies.domain.Movie;
import com.joseangelmaneiro.movies.domain.interactor.UseCase;
import com.joseangelmaneiro.movies.domain.interactor.UseCaseFactory;
import com.joseangelmaneiro.movies.presentation.MovieCellView;
import com.joseangelmaneiro.movies.presentation.MovieListView;
import java.lang.ref.WeakReference;
import java.util.List;

import javax.inject.Inject;


public class MovieListPresenter implements Handler<List<Movie>>{

    private UseCaseFactory useCaseFactory;

    private WeakReference<MovieListView> view;

    private List<Movie> movieList;

    private int selectedMovieId;

    @Inject
    public MovieListPresenter(UseCaseFactory useCaseFactory){
        this.useCaseFactory = useCaseFactory;
    }

    public void setView(MovieListView movieListView){
        view = new WeakReference<>(movieListView);
    }

    public void viewReady(){
        invokeUseCase();
    }

    public void refresh(){
        invokeUseCase();
    }

    @Override
    public void handle(List<Movie> movieList) {
        saveMovies(movieList);
        MovieListView movieListView = view.get();
        if(movieListView!=null){
            movieListView.cancelRefreshDialog();
            movieListView.refreshList();
        }
    }

    @Override
    public void error(Exception exception) {
        MovieListView movieListView = view.get();
        if(movieListView!=null){
            movieListView.cancelRefreshDialog();
            movieListView.showErrorMessage(exception.getMessage());
        }
    }

    public int getItemsCount(){
        if(moviesListIsEmpty()){
            return 0;
        } else{
            return movieList.size();
        }
    }

    public void configureCell(MovieCellView movieCellView, int position){
        Movie movie = getMovie(position);
        movieCellView.displayImage(movie.getPosterPath());
    }

    public void onItemClick(int position){
        Movie movie = getMovie(position);
        saveSelectedMovieId(movie.getId());
        MovieListView movieListView = view.get();
        if(movieListView!=null){
            movieListView.navigateToDetailScreen(getSelectedMovieId());
        }
    }

    private void invokeUseCase(){
        UseCase useCase = useCaseFactory.getMovies();
        useCase.execute(this, null);
    }

    public void saveMovies(List<Movie> movieList){
        this.movieList = movieList;
    }

    public Movie getMovie(int position){
        return movieList.get(position);
    }

    public void saveSelectedMovieId(int selectedMovieId){
        this.selectedMovieId = selectedMovieId;
    }

    public boolean moviesListIsEmpty(){
        return movieList==null || movieList.isEmpty();
    }

    public int getSelectedMovieId(){
        return selectedMovieId;
    }

}
