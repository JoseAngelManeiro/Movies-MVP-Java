package com.joseangelmaneiro.movies.ui.list;

import com.joseangelmaneiro.movies.data.Handler;
import com.joseangelmaneiro.movies.data.Movie;
import com.joseangelmaneiro.movies.data.MoviesRepository;
import com.joseangelmaneiro.movies.ui.MovieMapper;
import com.joseangelmaneiro.movies.ui.MovieViewModel;
import java.lang.ref.WeakReference;
import java.util.List;


public class MovieListPresenter implements Handler<List<Movie>>{

    private MoviesRepository repository;
    private MovieMapper movieMapper;
    private WeakReference<MovieListView> view;


    public MovieListPresenter(MoviesRepository repository, MovieMapper movieMapper){
        this.repository = repository;
        this.movieMapper = movieMapper;
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
    public void handle(List<Movie> movieList) {
        MovieListView movieListView = view.get();
        if(movieListView!=null){
            movieListView.cancelRefreshDialog();
            movieListView.refreshList(movieMapper.transform(movieList));
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

    public void onItemClick(MovieViewModel movieViewModel){
        MovieListView movieListView = view.get();
        if(movieListView!=null){
            movieListView.navigateToDetailScreen(movieViewModel.getId());
        }
    }

}
