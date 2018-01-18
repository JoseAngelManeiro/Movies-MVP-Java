package com.joseangelmaneiro.movies.data.source.local;

import com.joseangelmaneiro.movies.domain.Handler;
import com.joseangelmaneiro.movies.data.entity.MovieEntity;
import java.util.List;


public interface MoviesLocalDataSource {

    void getMovies(Handler<List<MovieEntity>> handler);

    void getMovie(int movieId, Handler<MovieEntity> handler);

    void saveMovies(List<MovieEntity> movieList);

    void deleteAllMovies();

}
