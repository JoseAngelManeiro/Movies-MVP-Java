package com.joseangelmaneiro.movies.domain.interactor;

import com.joseangelmaneiro.movies.domain.Movie;
import com.joseangelmaneiro.movies.domain.MoviesRepository;
import com.joseangelmaneiro.movies.domain.executor.JobScheduler;
import com.joseangelmaneiro.movies.domain.executor.UIScheduler;
import java.util.List;
import io.reactivex.Single;


public class GetMovies extends UseCase<List<Movie>, GetMovies.Params> {

    private MoviesRepository repository;


    public GetMovies(MoviesRepository repository,
                     UIScheduler uiScheduler,
                     JobScheduler jobScheduler){
        super(uiScheduler, jobScheduler);
        this.repository = repository;
    }

    @Override
    Single<List<Movie>> buildUseCaseObservable(GetMovies.Params params) {
        return Single.create(emitter -> {
            try {
                List<Movie> movieList = repository.getMovies(params.isOnlyOnline());
                emitter.onSuccess(movieList);
            } catch (Exception exception){
                if (!emitter.isDisposed()) {
                    emitter.onError(exception);
                }
            }
        });
    }

    public static final class Params{

        private final boolean onlyOnline;

        public Params(boolean onlyOnline){
            this.onlyOnline = onlyOnline;
        }

        public boolean isOnlyOnline() {
            return onlyOnline;
        }
    }

}
