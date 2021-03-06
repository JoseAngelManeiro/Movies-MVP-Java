package com.joseangelmaneiro.movies.domain.interactor;

import com.joseangelmaneiro.movies.domain.Movie;
import com.joseangelmaneiro.movies.domain.MoviesRepository;
import com.joseangelmaneiro.movies.domain.executor.JobScheduler;
import com.joseangelmaneiro.movies.domain.executor.UIScheduler;
import io.reactivex.Single;


public class GetMovie extends UseCase<Movie, GetMovie.Params> {

    private MoviesRepository repository;


    GetMovie(MoviesRepository repository,
             UIScheduler uiScheduler,
             JobScheduler jobScheduler){
        super(uiScheduler, jobScheduler);
        this.repository = repository;
    }

    @Override
    Single<Movie> buildUseCaseObservable(Params params) {
        return Single.just(repository.getMovie(params.getMovieId()));
    }

    public static final class Params{

        private final int movieId;

        public Params(int movieId){
            this.movieId = movieId;
        }

        public int getMovieId() {
            return movieId;
        }

    }

}
