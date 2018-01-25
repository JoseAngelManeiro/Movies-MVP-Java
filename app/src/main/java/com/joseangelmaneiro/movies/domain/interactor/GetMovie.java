package com.joseangelmaneiro.movies.domain.interactor;

import com.joseangelmaneiro.movies.domain.Handler;
import com.joseangelmaneiro.movies.domain.Movie;
import com.joseangelmaneiro.movies.domain.MoviesRepository;


public class GetMovie implements UseCase<Movie, GetMovie.Params> {

    private MoviesRepository repository;


    public GetMovie(MoviesRepository repository){
        this.repository = repository;
    }

    @Override
    public void execute(final Handler<Movie> handler, Params params) {
        repository.getMovie(params.getMovieId(), new Handler<Movie>() {
            @Override
            public void handle(Movie movie) {
                handler.handle(movie);
            }

            @Override
            public void error() {
                handler.error();
            }
        });
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
