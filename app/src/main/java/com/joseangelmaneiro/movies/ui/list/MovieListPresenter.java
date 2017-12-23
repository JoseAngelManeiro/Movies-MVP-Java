package com.joseangelmaneiro.movies.ui.list;

import com.joseangelmaneiro.movies.data.Handler;
import com.joseangelmaneiro.movies.data.Movie;
import com.joseangelmaneiro.movies.data.MoviesRepository;
import com.joseangelmaneiro.movies.ui.Formatter;
import java.lang.ref.WeakReference;
import java.util.List;


public class MovieListPresenter implements Handler<List<Movie>>{

    private MoviesRepository repository;

    private WeakReference<MovieListView> view;

    private List<Movie> movieList;

    private int selectedMovieId;


    public MovieListPresenter(MoviesRepository repository){
        this.repository = repository;
    }

    public void setView(MovieListView movieListView){
        view = new WeakReference<>(movieListView);
    }

    public void viewReady(){
        invokeGetMovies();
    }

    public void refresh(){
        invokeGetMovies();
    }

    @Override
    public void handle(List<Movie> movieList) {
        saveMovies(movieList);
        MovieListView movieListView = view.get();
        if(movieListView!=null){
            movieListView.cancelLoadingDialog();
            movieListView.refresh();
        }
    }

    @Override
    public void error() {
        MovieListView movieListView = view.get();
        if(movieListView!=null){
            movieListView.cancelLoadingDialog();
            movieListView.showErrorMessage();
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
        movieCellView.displayImage(Formatter.getCompleteUrlImage(movie.getPosterPath()));
    }

    public void onItemClick(int position){
        Movie movie = getMovie(position);
        saveSelectedMovieId(movie.getId());
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
        return movieList!=null && !movieList.isEmpty();
    }

    public int getSelectedMovieId(){
        return selectedMovieId;
    }

    private void invokeGetMovies(){
        repository.getMovies(this);
    }

}
