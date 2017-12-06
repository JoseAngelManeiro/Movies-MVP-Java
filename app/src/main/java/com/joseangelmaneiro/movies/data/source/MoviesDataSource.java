package com.joseangelmaneiro.movies.data.source;

import com.joseangelmaneiro.movies.data.Movie;

import java.util.List;


public interface MoviesDataSource {

    //Callback to comunicate presenter with repository
    public interface Handler<T> {

        void handle(T result);

        void error();

    }

    void getMovies(Handler<List<Movie>> handler);

    void getMovie(int movieId, Handler<Movie> handler);

    void saveMovies(List<Movie> movieList);

    void deleteAllMovies();

}
