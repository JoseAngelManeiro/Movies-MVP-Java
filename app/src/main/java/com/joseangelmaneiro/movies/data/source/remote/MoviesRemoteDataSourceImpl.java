package com.joseangelmaneiro.movies.data.source.remote;

import com.joseangelmaneiro.movies.domain.Handler;
import com.joseangelmaneiro.movies.data.entity.MovieEntity;
import com.joseangelmaneiro.movies.data.entity.PageEntity;
import com.joseangelmaneiro.movies.data.source.remote.net.MovieService;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MoviesRemoteDataSourceImpl implements MoviesRemoteDataSource {

    // TODO Put here your api key (https://developers.themoviedb.org/3/getting-started)
    private static final String API_KEY = "";

    private static MoviesRemoteDataSourceImpl INSTANCE;

    private MovieService movieService;


    // Prevent direct instantiation.
    private MoviesRemoteDataSourceImpl(MovieService movieService) {
        this.movieService = movieService;
    }

    public static MoviesRemoteDataSourceImpl getInstance(MovieService movieService) {
        if (INSTANCE == null) {
            INSTANCE = new MoviesRemoteDataSourceImpl(movieService);
        }
        return INSTANCE;
    }

    @Override
    public void getMovies(final Handler<List<MovieEntity>> handler) {
        movieService.getMovies(API_KEY).enqueue(new Callback<PageEntity>() {
            @Override
            public void onResponse(Call<PageEntity> call, Response<PageEntity> response) {
                PageEntity page = response.body();
                if(page!=null) {
                    handler.handle(page.movies);
                } else{
                    handler.error(new Exception());
                }
            }

            @Override
            public void onFailure(Call<PageEntity> call, Throwable t) {
                handler.error(new Exception());
            }
        });
    }

}
