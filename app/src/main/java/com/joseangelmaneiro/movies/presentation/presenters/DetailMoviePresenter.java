package com.joseangelmaneiro.movies.presentation.presenters;

import com.joseangelmaneiro.movies.domain.Handler;
import com.joseangelmaneiro.movies.domain.Movie;
import com.joseangelmaneiro.movies.domain.interactor.GetMovie;
import com.joseangelmaneiro.movies.domain.interactor.UseCase;
import com.joseangelmaneiro.movies.domain.interactor.UseCaseFactory;
import com.joseangelmaneiro.movies.presentation.DetailMovieView;
import com.joseangelmaneiro.movies.presentation.formatters.Formatter;
import java.lang.ref.WeakReference;


public class DetailMoviePresenter implements Handler<Movie>{

    private UseCaseFactory useCaseFactory;

    private Formatter formatter;

    private int movieId;

    private WeakReference<DetailMovieView> view;


    public DetailMoviePresenter(UseCaseFactory useCaseFactory, Formatter formatter, int movieId){
        this.useCaseFactory = useCaseFactory;
        this.formatter = formatter;
        this.movieId = movieId;
    }

    public void setView(DetailMovieView detailMovieView){
        view = new WeakReference<>(detailMovieView);
    }

    public void viewReady(){
        UseCase useCase = useCaseFactory.getMovie();
        useCase.execute(this, new GetMovie.Params(movieId));
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
    public void error(Exception ignored) { }

    public void navUpSelected(){
        DetailMovieView detailMovieView = view.get();
        if(detailMovieView!=null){
            detailMovieView.goToBack();
        }
    }

}
