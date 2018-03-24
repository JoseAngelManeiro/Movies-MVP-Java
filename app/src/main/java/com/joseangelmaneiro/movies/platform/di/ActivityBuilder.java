package com.joseangelmaneiro.movies.platform.di;

import android.app.Activity;
import com.joseangelmaneiro.movies.platform.di.detail.DetailActivityComponent;
import com.joseangelmaneiro.movies.platform.di.list.ListActivityComponent;
import com.joseangelmaneiro.movies.platform.views.DetailMovieActivity;
import com.joseangelmaneiro.movies.platform.views.MovieListActivity;
import dagger.Binds;
import dagger.Module;
import dagger.android.ActivityKey;
import dagger.android.AndroidInjector;
import dagger.multibindings.IntoMap;

@Module
public abstract class ActivityBuilder {

    @Binds
    @IntoMap
    @ActivityKey(MovieListActivity.class)
    abstract AndroidInjector.Factory<? extends Activity> bindListActivity(
            ListActivityComponent.Builder builder);

    @Binds
    @IntoMap
    @ActivityKey(DetailMovieActivity.class)
    abstract AndroidInjector.Factory<? extends Activity> bindDetailActivity(
            DetailActivityComponent.Builder builder);

}
