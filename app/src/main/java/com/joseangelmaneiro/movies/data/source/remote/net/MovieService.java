package com.joseangelmaneiro.movies.data.source.remote.net;

import com.joseangelmaneiro.movies.data.entity.PageEntity;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;


public interface MovieService {

    @GET("movie/popular")
    Call<PageEntity> getMovies(@Query("api_key") String apiKey);

}
