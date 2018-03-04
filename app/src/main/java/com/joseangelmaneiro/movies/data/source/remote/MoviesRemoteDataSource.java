package com.joseangelmaneiro.movies.data.source.remote;

import com.joseangelmaneiro.movies.domain.Handler;
import com.joseangelmaneiro.movies.data.entity.MovieEntity;
import java.util.List;


public interface MoviesRemoteDataSource {

    void getAll(Handler<List<MovieEntity>> handler);

}
