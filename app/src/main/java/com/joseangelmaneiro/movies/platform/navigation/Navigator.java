package com.joseangelmaneiro.movies.platform.navigation;

import android.app.Activity;
import com.joseangelmaneiro.movies.platform.DetailMovieActivity;


public class Navigator {

    public static void navigateToDetail(Activity activity, int movieId){
        if (activity != null) {
            DetailMovieActivity.launch(activity, movieId);
        }
    }

}
