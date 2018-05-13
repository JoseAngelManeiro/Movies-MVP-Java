package com.joseangelmaneiro.movies.ui.detail;

import com.joseangelmaneiro.movies.data.Handler;
import com.joseangelmaneiro.movies.data.Movie;
import com.joseangelmaneiro.movies.data.MoviesRepository;
import com.joseangelmaneiro.movies.ui.Formatter;
import java.lang.ref.WeakReference;


public class DetailMoviePresenter implements Handler<Movie>{

    private MoviesRepository repository;

    private Formatter formatter;

    private int movieId;

    private WeakReference<DetailMovieView> view;


    public DetailMoviePresenter(MoviesRepository repository, Formatter formatter, int movieId){
        this.repository = repository;
        this.formatter = formatter;
        this.movieId = movieId;
    }

    public void setView(DetailMovieView detailMovieView){
        view = new WeakReference<>(detailMovieView);
    }

    public void viewReady(){
        repository.getMovie(movieId, this);
    }

    @Override
    public void handle(Movie movie) {
        DetailMovieView detailMovieView = view.get();
        if(detailMovieView!=null){
            detailMovieView.displayImage(formatter.getCompleteUrlImage(movie.getBackdropPath()));
            detailMovieView.displayTitle(movie.getTitle());
            detailMovieView.displayVoteAverage(movie.getVoteAverage());
            detailMovieView.displayReleaseDate(formatter.formatDate(movie.getReleaseDate()));
            detailMovieView.displayOverview(movie.getOverview());
        }
    }

    @Override
    public void error() {
        DetailMovieView detailMovieView = view.get();
        if(detailMovieView!=null){
            detailMovieView.showErrorMessage();
        }
    }

    public void navUpSelected(){
        DetailMovieView detailMovieView = view.get();
        if(detailMovieView!=null){
            detailMovieView.goToBack();
        }
    }

}
