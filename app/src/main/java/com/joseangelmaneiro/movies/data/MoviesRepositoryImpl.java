package com.joseangelmaneiro.movies.data;

import com.joseangelmaneiro.movies.data.source.local.MoviesLocalDataSource;
import com.joseangelmaneiro.movies.data.source.remote.MoviesRemoteDataSource;
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
    public void getMovies(final Handler<List<Movie>> handler) {
        remoteDataSource.getMovies(new Handler<List<Movie>>() {
            @Override
            public void handle(List<Movie> movieList) {
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
    public void getMovie(int movieId, final Handler<Movie> handler) {
        localDataSource.getMovie(movieId, new Handler<Movie>() {
            @Override
            public void handle(Movie movie) {
                handler.handle(movie);
            }

            @Override
            public void error() {
                handler.error();
            }
        });
    }

}
