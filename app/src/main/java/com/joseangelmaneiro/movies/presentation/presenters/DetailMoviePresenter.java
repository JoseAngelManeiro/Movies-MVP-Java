package com.joseangelmaneiro.movies.presentation.presenters;

import com.joseangelmaneiro.movies.domain.Handler;
import com.joseangelmaneiro.movies.domain.Movie;
import com.joseangelmaneiro.movies.domain.MoviesRepository;
import com.joseangelmaneiro.movies.presentation.DetailMovieView;
import com.joseangelmaneiro.movies.presentation.formatters.Formatter;
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
