package com.joseangelmaneiro.movies.data.source.local;

import com.joseangelmaneiro.movies.domain.Handler;
import com.joseangelmaneiro.movies.data.Movie;
import java.util.List;


public interface MoviesLocalDataSource {

    void getMovies(Handler<List<Movie>> handler);

    void getMovie(int movieId, Handler<Movie> handler);

    void saveMovies(List<Movie> movieList);

    void deleteAllMovies();

}
