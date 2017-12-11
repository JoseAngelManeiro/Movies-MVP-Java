package com.joseangelmaneiro.movies.data.source.remote;

import com.joseangelmaneiro.movies.data.Handler;
import com.joseangelmaneiro.movies.data.Movie;
import java.util.List;


public interface MoviesRemoteDataSource {

    void getMovies(Handler<List<Movie>> handler);

}
