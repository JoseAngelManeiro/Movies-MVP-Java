package com.joseangelmaneiro.movies.domain.interactor;

import com.joseangelmaneiro.movies.domain.Handler;
import com.joseangelmaneiro.movies.domain.Movie;
import com.joseangelmaneiro.movies.domain.MoviesRepository;
import java.util.List;


public class GetMovies implements UseCase<List<Movie>, Void> {

    private MoviesRepository repository;


    public GetMovies(MoviesRepository repository){
        this.repository = repository;
    }

    @Override
    public void execute(final Handler<List<Movie>> handler, Void unused) {
        repository.getMovies(new Handler<List<Movie>>() {
            @Override
            public void handle(List<Movie> movieList) {
                handler.handle(movieList);
            }

            @Override
            public void error() {
                handler.error();
            }
        });
    }

}
