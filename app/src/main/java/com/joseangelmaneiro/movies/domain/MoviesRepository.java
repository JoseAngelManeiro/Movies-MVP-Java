package com.joseangelmaneiro.movies.domain;

import com.joseangelmaneiro.movies.data.entity.MovieEntity;

import java.util.List;


public interface MoviesRepository {

    void getMovies(Handler<List<MovieEntity>> handler);

    void getMovie(int movieId, Handler<MovieEntity> handler);

}
