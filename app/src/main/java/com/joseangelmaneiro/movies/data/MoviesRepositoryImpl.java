package com.joseangelmaneiro.movies.data;

import com.joseangelmaneiro.movies.data.entity.MovieEntity;
import com.joseangelmaneiro.movies.data.source.local.MoviesLocalDataSource;
import com.joseangelmaneiro.movies.data.source.remote.MoviesRemoteDataSource;
import com.joseangelmaneiro.movies.domain.Handler;
import com.joseangelmaneiro.movies.domain.MoviesRepository;

import java.util.List;


public class MoviesRepositoryImpl implements MoviesRepository {

    private static MoviesRepositoryImpl INSTANCE;

    private MoviesLocalDataSource localDataSource;

    private MoviesRemoteDataSource remoteDataSource;


    // Prevent direct instantiation.
    private MoviesRepositoryImpl(MoviesLocalDataSource localDataSource,
                                 MoviesRemoteDataSource remoteDataSource) {
        this.localDataSource = localDataSource;
        this.remoteDataSource = remoteDataSource;
    }

    public static MoviesRepositoryImpl getInstance(MoviesLocalDataSource localDataSource,
                                                   MoviesRemoteDataSource remoteDataSource) {
        if (INSTANCE == null) {
            INSTANCE = new MoviesRepositoryImpl(localDataSource, remoteDataSource);
        }
        return INSTANCE;
    }

    public static void destroyInstance() {
        INSTANCE = null;
    }

    @Override
    public void getMovies(final Handler<List<MovieEntity>> handler) {
        remoteDataSource.getMovies(new Handler<List<MovieEntity>>() {
            @Override
            public void handle(List<MovieEntity> movieList) {
                localDataSource.deleteAllMovies();
                localDataSource.saveMovies(movieList);
                handler.handle(movieList);
            }

            @Override
            public void error() {
                handler.error();
            }
        });
    }

    @Override
    public void getMovie(int movieId, final Handler<MovieEntity> handler) {
        localDataSource.getMovie(movieId, new Handler<MovieEntity>() {
            @Override
            public void handle(MovieEntity movie) {
                handler.handle(movie);
            }

            @Override
            public void error() {
                handler.error();
            }
        });
    }

}
