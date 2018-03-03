package com.joseangelmaneiro.movies.data.source.local;

import com.joseangelmaneiro.movies.domain.Handler;
import com.joseangelmaneiro.movies.data.entity.MovieEntity;
import java.util.List;


public interface MoviesLocalDataSource {

    void getAll(Handler<List<MovieEntity>> handler);

    void get(int movieId, Handler<MovieEntity> handler);

    void save(List<MovieEntity> movieEntityList);

    void deleteAll();

}
