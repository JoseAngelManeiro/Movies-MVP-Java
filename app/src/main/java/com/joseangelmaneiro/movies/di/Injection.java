package com.joseangelmaneiro.movies.di;

import android.content.Context;
import com.joseangelmaneiro.movies.data.entity.mapper.EntityDataMapper;
import com.joseangelmaneiro.movies.data.MoviesRepositoryImpl;
import com.joseangelmaneiro.movies.data.source.local.MoviesLocalDataSource;
import com.joseangelmaneiro.movies.data.source.local.MoviesLocalDataSourceImpl;
import com.joseangelmaneiro.movies.data.source.local.db.MoviesDatabaseHelper;
import com.joseangelmaneiro.movies.data.source.remote.MoviesRemoteDataSource;
import com.joseangelmaneiro.movies.data.source.remote.MoviesRemoteDataSourceImpl;
import com.joseangelmaneiro.movies.data.source.remote.net.RetrofitClient;
import com.joseangelmaneiro.movies.domain.interactor.UseCaseFactory;


public class Injection {

    public static UseCaseFactory provideUseCaseFactory(Context context){
        return new UseCaseFactory(MoviesRepositoryImpl.getInstance(
                getLocalDataSource(context),
                getRemoteDataSource(),
                new EntityDataMapper()));
    }

    private static MoviesLocalDataSource getLocalDataSource(Context context){
        return MoviesLocalDataSourceImpl.getInstance(MoviesDatabaseHelper.getInstance(context));
    }

    private static MoviesRemoteDataSource getRemoteDataSource(){
        return MoviesRemoteDataSourceImpl.getInstance(RetrofitClient.getInstance());
    }

}
