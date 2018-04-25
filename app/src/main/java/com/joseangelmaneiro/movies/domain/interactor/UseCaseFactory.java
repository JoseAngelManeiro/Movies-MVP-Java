package com.joseangelmaneiro.movies.domain.interactor;

import com.joseangelmaneiro.movies.domain.MoviesRepository;
import com.joseangelmaneiro.movies.domain.executor.JobScheduler;
import com.joseangelmaneiro.movies.domain.executor.UIScheduler;
import javax.inject.Inject;


public class UseCaseFactory {

    private MoviesRepository repository;
    private UIScheduler uiScheduler;
    private JobScheduler jobScheduler;

    @Inject
    public UseCaseFactory(MoviesRepository repository,
                          UIScheduler uiScheduler,
                          JobScheduler jobScheduler){
        this.repository = repository;
        this.uiScheduler = uiScheduler;
        this.jobScheduler = jobScheduler;
    }

    public UseCase getMovie(){
        return new GetMovie(repository, uiScheduler, jobScheduler);
    }

    public UseCase getMovies(){
        return new GetMovies(repository, uiScheduler, jobScheduler);
    }

}
