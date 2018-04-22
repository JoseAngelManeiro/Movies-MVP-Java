package com.joseangelmaneiro.movies.data.source.remote;

import com.joseangelmaneiro.movies.data.entity.MovieEntity;
import java.util.List;


public interface MoviesRemoteDataSource {

    List<MovieEntity> getAll() throws Exception;

}
