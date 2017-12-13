package com.joseangelmaneiro.movies.data.source.remote;

import com.joseangelmaneiro.movies.data.Handler;
import com.joseangelmaneiro.movies.data.Movie;
import com.joseangelmaneiro.movies.data.Page;
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
    public void getMovies(final Handler<List<Movie>> handler) {
        movieService.getMovies(API_KEY).enqueue(new Callback<Page>() {
            @Override
            public void onResponse(Call<Page> call, Response<Page> response) {
                handler.handle(response.body().getMovies());
            }

            @Override
            public void onFailure(Call<Page> call, Throwable t) {
                handler.error();
            }
        });
    }

}
