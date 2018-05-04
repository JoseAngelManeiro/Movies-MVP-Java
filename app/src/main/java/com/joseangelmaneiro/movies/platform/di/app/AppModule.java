package com.joseangelmaneiro.movies.platform.di.app;

import android.app.Application;
import android.content.Context;
import android.database.sqlite.SQLiteOpenHelper;
import com.joseangelmaneiro.movies.data.MoviesRepositoryImpl;
import com.joseangelmaneiro.movies.data.entity.mapper.EntityDataMapper;
import com.joseangelmaneiro.movies.data.executor.JobThread;
import com.joseangelmaneiro.movies.data.source.local.MoviesDatabaseHelper;
import com.joseangelmaneiro.movies.data.source.local.MoviesLocalDataSource;
import com.joseangelmaneiro.movies.data.source.local.MoviesLocalDataSourceImpl;
import com.joseangelmaneiro.movies.data.source.remote.MovieService;
import com.joseangelmaneiro.movies.data.source.remote.MoviesRemoteDataSource;
import com.joseangelmaneiro.movies.data.source.remote.MoviesRemoteDataSourceImpl;
import com.joseangelmaneiro.movies.domain.MoviesRepository;
import com.joseangelmaneiro.movies.domain.executor.JobScheduler;
import com.joseangelmaneiro.movies.domain.executor.UIScheduler;
import com.joseangelmaneiro.movies.platform.executor.UIThread;
import javax.inject.Singleton;
import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


@Module
public class AppModule {

    @Provides
    @Singleton
    public Context provideContext(Application application) {
        return application;
    }

    @Provides
    @Singleton
    public SQLiteOpenHelper provideSQLiteOpenHelper(Context context) {
        return new MoviesDatabaseHelper(context);
    }

    @Provides
    @Singleton
    public MovieService provideService() {
        return new Retrofit.Builder()
                .baseUrl("https://api.themoviedb.org/3/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(MovieService.class);
    }

    @Provides
    @Singleton
    public MoviesLocalDataSource provideLocalDataSource(SQLiteOpenHelper sqLiteOpenHelper) {
        return new MoviesLocalDataSourceImpl(sqLiteOpenHelper);
    }

    @Provides
    @Singleton
    public MoviesRemoteDataSource provideRemoteDataSource(MovieService movieService) {
        return new MoviesRemoteDataSourceImpl(movieService);
    }

    @Provides
    @Singleton
    public MoviesRepository provideRepository(MoviesLocalDataSource localDataSource,
                                       MoviesRemoteDataSource remoteDataSource,
                                       EntityDataMapper entityDataMapper) {
        return new MoviesRepositoryImpl(localDataSource, remoteDataSource, entityDataMapper);
    }

    @Provides
    @Singleton
    public JobScheduler provideJobScheduler(JobThread jobThread){
        return jobThread;
    }

    @Provides
    @Singleton
    public UIScheduler provideUIScheduler(UIThread uiThread){
        return uiThread;
    }

}
