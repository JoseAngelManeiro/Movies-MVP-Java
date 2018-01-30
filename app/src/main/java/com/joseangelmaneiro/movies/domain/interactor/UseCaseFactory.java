package com.joseangelmaneiro.movies.domain.interactor;

import com.joseangelmaneiro.movies.domain.MoviesRepository;


public class UseCaseFactory {

    private MoviesRepository repository;


    public UseCaseFactory(MoviesRepository repository){
        this.repository = repository;
    }

    public UseCase getMovie(){
        return new GetMovie(repository);
    }

}
