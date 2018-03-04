package com.joseangelmaneiro.movies.data;

import com.joseangelmaneiro.movies.data.entity.MovieEntity;
import com.joseangelmaneiro.movies.data.entity.mapper.EntityDataMapper;
import com.joseangelmaneiro.movies.data.source.local.MoviesLocalDataSource;
import com.joseangelmaneiro.movies.data.source.remote.MoviesRemoteDataSource;
import com.joseangelmaneiro.movies.domain.Handler;
import com.joseangelmaneiro.movies.domain.Movie;
import com.joseangelmaneiro.movies.domain.MoviesRepository;
import java.util.List;


public class MoviesRepositoryImpl implements MoviesRepository {

    private static MoviesRepositoryImpl INSTANCE;

    private MoviesLocalDataSource localDataSource;

    private MoviesRemoteDataSource remoteDataSource;

    private EntityDataMapper entityDataMapper;


    // Prevent direct instantiation.
    private MoviesRepositoryImpl(MoviesLocalDataSource localDataSource,
                                 MoviesRemoteDataSource remoteDataSource,
                                 EntityDataMapper entityDataMapper) {
        this.localDataSource = localDataSource;
        this.remoteDataSource = remoteDataSource;
        this.entityDataMapper = entityDataMapper;
    }

    public static MoviesRepositoryImpl getInstance(MoviesLocalDataSource localDataSource,
                                                   MoviesRemoteDataSource remoteDataSource,
                                                   EntityDataMapper entityDataMapper) {
        if (INSTANCE == null) {
            INSTANCE = new MoviesRepositoryImpl(localDataSource, remoteDataSource, entityDataMapper);
        }
        return INSTANCE;
    }

    public static void destroyInstance() {
        INSTANCE = null;
    }

    @Override
    public void getMovies(final Handler<List<Movie>> handler) {
        remoteDataSource.getAll(new Handler<List<MovieEntity>>() {
            @Override
            public void handle(List<MovieEntity> movieEntityList) {
                localDataSource.deleteAll();
                localDataSource.save(movieEntityList);
                handler.handle(entityDataMapper.transform(movieEntityList));
            }
            @Override
            public void error(Exception exception) {
                handler.error(exception);
            }
        });
    }

    @Override
    public void getMovie(int movieId, final Handler<Movie> handler) {
        localDataSource.get(movieId, new Handler<MovieEntity>() {
            @Override
            public void handle(MovieEntity movieEntity) {
                handler.handle(entityDataMapper.transform(movieEntity));
            }

            @Override
            public void error(Exception ignored) { }
        });
    }

}
