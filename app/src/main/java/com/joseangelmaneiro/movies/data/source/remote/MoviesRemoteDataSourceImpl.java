package com.joseangelmaneiro.movies.data.source.remote;

import com.joseangelmaneiro.movies.data.exception.NetworkConnectionException;
import com.joseangelmaneiro.movies.data.exception.ServiceException;
import com.joseangelmaneiro.movies.data.entity.MovieEntity;
import com.joseangelmaneiro.movies.data.entity.PageEntity;
import java.io.IOException;
import java.util.List;
import javax.inject.Inject;
import retrofit2.Response;


public class MoviesRemoteDataSourceImpl implements MoviesRemoteDataSource {

    // TODO Put here your api key (https://developers.themoviedb.org/3/getting-started)
    private static final String API_KEY = "";

    private MovieService service;

    @Inject
    public MoviesRemoteDataSourceImpl(MovieService service) {
        this.service = service;
    }

    @Override
    public List<MovieEntity> getAll() throws Exception {
        try {
            Response<PageEntity> response = service.getPageEntity(API_KEY).execute();
            if(response.isSuccessful()){
                return response.body().movies;
            } else{
                throw new ServiceException();
            }
        } catch (IOException exception) {
            throw new NetworkConnectionException();
        }
    }

}
