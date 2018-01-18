package com.joseangelmaneiro.movies.ui.list;

import com.joseangelmaneiro.movies.domain.Handler;
import com.joseangelmaneiro.movies.data.entity.MovieEntity;
import com.joseangelmaneiro.movies.domain.MoviesRepository;
import com.joseangelmaneiro.movies.ui.Formatter;
import java.lang.ref.WeakReference;
import java.util.List;


public class MovieListPresenter implements Handler<List<MovieEntity>>{

    private MoviesRepository repository;

    private Formatter formatter;

    private WeakReference<MovieListView> view;

    private List<MovieEntity> movieList;

    private int selectedMovieId;


    public MovieListPresenter(MoviesRepository repository, Formatter formatter){
        this.repository = repository;
        this.formatter = formatter;
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

    public void invokeGetMovies(){
        repository.getMovies(this);
    }

    @Override
    public void handle(List<MovieEntity> movieList) {
        saveMovies(movieList);
        MovieListView movieListView = view.get();
        if(movieListView!=null){
            movieListView.cancelRefreshDialog();
            movieListView.refreshList();
        }
    }

    @Override
    public void error() {
        MovieListView movieListView = view.get();
        if(movieListView!=null){
            movieListView.cancelRefreshDialog();
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
        MovieEntity movie = getMovie(position);
        movieCellView.displayImage(formatter.getCompleteUrlImage(movie.getPosterPath()));
    }

    public void onItemClick(int position){
        MovieEntity movie = getMovie(position);
        saveSelectedMovieId(movie.getId());
        MovieListView movieListView = view.get();
        if(movieListView!=null){
            movieListView.navigateToDetailScreen(getSelectedMovieId());
        }
    }

    public void saveMovies(List<MovieEntity> movieList){
        this.movieList = movieList;
    }

    public MovieEntity getMovie(int position){
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
