package com.joseangelmaneiro.movies.data;

import com.joseangelmaneiro.movies.data.entity.MovieEntity;
import com.joseangelmaneiro.movies.data.entity.mapper.EntityDataMapper;
import com.joseangelmaneiro.movies.data.source.local.MoviesLocalDataSource;
import com.joseangelmaneiro.movies.data.source.remote.MoviesRemoteDataSource;
import com.joseangelmaneiro.movies.domain.Handler;
import com.joseangelmaneiro.movies.domain.Movie;
import com.joseangelmaneiro.movies.domain.MoviesRepository;
import java.util.List;
import javax.inject.Inject;


public class MoviesRepositoryImpl implements MoviesRepository {

    private MoviesLocalDataSource localDataSource;

    private MoviesRemoteDataSource remoteDataSource;

    private EntityDataMapper entityDataMapper;


    @Inject
    public MoviesRepositoryImpl(MoviesLocalDataSource localDataSource,
                                 MoviesRemoteDataSource remoteDataSource,
                                 EntityDataMapper entityDataMapper) {
        this.localDataSource = localDataSource;
        this.remoteDataSource = remoteDataSource;
        this.entityDataMapper = entityDataMapper;
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
        MovieEntity movieEntity = localDataSource.get(movieId);
        handler.handle(entityDataMapper.transform(movieEntity));
    }

}
