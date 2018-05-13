package com.joseangelmaneiro.movies.data.source.remote.net;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class RetrofitClient {

    private static final String BASE_URL = "https://api.themoviedb.org/3/";

    private static MovieService INSTANCE;


    public static synchronized MovieService getInstance(){
        if(INSTANCE == null){
            INSTANCE = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                    .create(MovieService.class);
        }
        return INSTANCE;
    }

}
