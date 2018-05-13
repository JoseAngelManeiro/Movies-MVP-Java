package com.joseangelmaneiro.movies.data.source.local;

import com.joseangelmaneiro.movies.data.entity.MovieEntity;
import java.util.List;


public interface MoviesLocalDataSource {

    List<MovieEntity> getAll();

    MovieEntity get(int id);

    void save(List<MovieEntity> movieEntityList);

    void deleteAll();

}
