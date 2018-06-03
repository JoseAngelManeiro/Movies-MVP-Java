package com.joseangelmaneiro.movies.presentation.presenters;

import com.joseangelmaneiro.movies.domain.Movie;
import com.joseangelmaneiro.movies.domain.Observer;
import com.joseangelmaneiro.movies.domain.interactor.GetMovie;
import com.joseangelmaneiro.movies.domain.interactor.UseCase;
import com.joseangelmaneiro.movies.domain.interactor.UseCaseFactory;
import com.joseangelmaneiro.movies.presentation.DetailMovieView;
import com.joseangelmaneiro.movies.presentation.formatters.Formatter;
import java.lang.ref.WeakReference;
import javax.inject.Inject;


public class DetailMoviePresenter extends BasePresenter {

    private UseCaseFactory useCaseFactory;

    private Formatter formatter;

    private int movieId;

    private WeakReference<DetailMovieView> view;

    @Inject
    public DetailMoviePresenter(UseCaseFactory useCaseFactory,
                                Formatter formatter,
                                int movieId){
        this.useCaseFactory = useCaseFactory;
        this.formatter = formatter;
        this.movieId = movieId;
    }

    public void setView(DetailMovieView detailMovieView){
        view = new WeakReference<>(detailMovieView);
    }

    public void viewReady(){
        UseCase useCase = useCaseFactory.getMovie();
        addDisposable(useCase.execute(new MovieObserver(), new GetMovie.Params(movieId)));
    }

    private final class MovieObserver extends Observer<Movie> {

        @Override
        public void onSuccess(Movie movie) {
            DetailMovieView detailMovieView = view.get();
            if(detailMovieView!=null){
                detailMovieView.displayImage(movie.getBackdropPath());
                detailMovieView.displayTitle(movie.getTitle());
                detailMovieView.displayVoteAverage(movie.getVoteAverage());
                detailMovieView.displayReleaseDate(formatter.formatDate(movie.getReleaseDate()));
                detailMovieView.displayOverview(movie.getOverview());
            }
        }

        @Override
        public void onError(Throwable ignored) {}

    }

    public void navUpSelected(){
        DetailMovieView detailMovieView = view.get();
        if(detailMovieView!=null){
            detailMovieView.goToBack();
        }
    }

}
