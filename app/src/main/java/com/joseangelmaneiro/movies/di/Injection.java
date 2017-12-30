package com.joseangelmaneiro.movies.di;

import android.content.Context;
import com.joseangelmaneiro.movies.data.MoviesRepository;
import com.joseangelmaneiro.movies.data.MoviesRepositoryImpl;
import com.joseangelmaneiro.movies.data.source.local.MoviesLocalDataSource;
import com.joseangelmaneiro.movies.data.source.local.MoviesLocalDataSourceImpl;
import com.joseangelmaneiro.movies.data.source.local.db.MoviesDatabaseHelper;
import com.joseangelmaneiro.movies.data.source.remote.MoviesRemoteDataSource;
import com.joseangelmaneiro.movies.data.source.remote.MoviesRemoteDataSourceImpl;
import com.joseangelmaneiro.movies.data.source.remote.net.RetrofitClient;


public class Injection {

    public static MoviesRepository provideRepository(Context context){
        return MoviesRepositoryImpl.getInstance(getLocalDataSource(context), getRemoteDataSource());
    }

    private static MoviesLocalDataSource getLocalDataSource(Context context){
        return MoviesLocalDataSourceImpl.getInstance(MoviesDatabaseHelper.getInstance(context));
    }

    private static MoviesRemoteDataSource getRemoteDataSource(){
        return MoviesRemoteDataSourceImpl.getInstance(RetrofitClient.getInstance());
    }

}
