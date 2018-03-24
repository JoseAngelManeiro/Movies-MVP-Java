package com.joseangelmaneiro.movies.domain.interactor;

import com.joseangelmaneiro.movies.domain.MoviesRepository;

import javax.inject.Inject;


public class UseCaseFactory {

    private MoviesRepository repository;

    @Inject
    public UseCaseFactory(MoviesRepository repository){
        this.repository = repository;
    }

    public UseCase getMovie(){
        return new GetMovie(repository);
    }

    public UseCase getMovies(){
        return new GetMovies(repository);
    }

}
