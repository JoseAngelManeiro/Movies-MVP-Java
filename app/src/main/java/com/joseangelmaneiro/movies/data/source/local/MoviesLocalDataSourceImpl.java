package com.joseangelmaneiro.movies.data.source.local;

import com.joseangelmaneiro.movies.domain.Handler;
import com.joseangelmaneiro.movies.data.entity.MovieEntity;
import com.joseangelmaneiro.movies.data.source.local.db.MoviesDatabaseHelper;
import java.util.List;


public class MoviesLocalDataSourceImpl implements MoviesLocalDataSource {

    private static MoviesLocalDataSourceImpl INSTANCE;

    private MoviesDatabaseHelper moviesDatabaseHelper;

    // Prevent direct instantiation.
    private MoviesLocalDataSourceImpl(MoviesDatabaseHelper moviesDatabaseHelper) {
        this.moviesDatabaseHelper = moviesDatabaseHelper;
    }

    public static MoviesLocalDataSourceImpl getInstance(MoviesDatabaseHelper moviesDatabaseHelper) {
        if (INSTANCE == null) {
            INSTANCE = new MoviesLocalDataSourceImpl(moviesDatabaseHelper);
        }
        return INSTANCE;
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
