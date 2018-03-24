package com.joseangelmaneiro.movies.data.source.local;

import com.joseangelmaneiro.movies.domain.Handler;
import com.joseangelmaneiro.movies.data.entity.MovieEntity;

import java.util.List;
import javax.inject.Inject;


public class MoviesLocalDataSourceImpl implements MoviesLocalDataSource {

    private MoviesDatabaseHelper moviesDatabaseHelper;

    @Inject
    public MoviesLocalDataSourceImpl(MoviesDatabaseHelper moviesDatabaseHelper) {
        this.moviesDatabaseHelper = moviesDatabaseHelper;
    }

    @Override
    public void getAll(Handler<List<MovieEntity>> handler) {
        handler.handle(moviesDatabaseHelper.getAll());
    }

    @Override
    public void get(int movieId, Handler<MovieEntity> handler) {
        handler.handle(moviesDatabaseHelper.get(movieId));
    }

    @Override
    public void save(List<MovieEntity> movieList) {
        moviesDatabaseHelper.save(movieList);
    }

    @Override
    public void deleteAll() {
        moviesDatabaseHelper.deleteAll();
    }

}
