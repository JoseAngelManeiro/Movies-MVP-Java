package com.joseangelmaneiro.movies.data;

import java.util.List;


public interface MoviesRepository {

    void getMovies(Handler<List<Movie>> handler);

    void getMovie(int movieId, Handler<Movie> handler);

}
