package com.joseangelmaneiro.movies.domain;

import com.joseangelmaneiro.movies.data.Movie;

import java.util.List;


public interface MoviesRepository {

    void getMovies(Handler<List<Movie>> handler);

    void getMovie(int movieId, Handler<Movie> handler);

}
