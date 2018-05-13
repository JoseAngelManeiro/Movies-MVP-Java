package com.joseangelmaneiro.movies.data;

import com.joseangelmaneiro.movies.data.entity.MovieEntity;
import com.joseangelmaneiro.movies.data.entity.mapper.EntityDataMapper;
import com.joseangelmaneiro.movies.data.source.local.MoviesLocalDataSource;
import com.joseangelmaneiro.movies.data.source.remote.MoviesRemoteDataSource;
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
    public List<Movie> getMovies(boolean onlyOnline) throws Exception {
        List<MovieEntity> movieEntityList;
        if(onlyOnline){
            movieEntityList = remoteDataSource.getAll();
            saveData(movieEntityList);
        } else{
            movieEntityList = localDataSource.getAll();
            if(movieEntityList.isEmpty()){
                movieEntityList = remoteDataSource.getAll();
                saveData(movieEntityList);
            }
        }
        return entityDataMapper.transform(movieEntityList);
    }

    private void saveData(List<MovieEntity> movieEntityList){
        localDataSource.deleteAll();
        localDataSource.save(movieEntityList);
    }

    @Override
    public Movie getMovie(int movieId) {
        MovieEntity movieEntity = localDataSource.get(movieId);
        return entityDataMapper.transform(movieEntity);
    }

}
