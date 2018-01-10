package com.joseangelmaneiro.movies.ui.detail;

import com.joseangelmaneiro.movies.ui.BaseView;


public interface DetailMovieView extends BaseView {

    void displayImage(String url);

    void displayTitle(String title);

    void displayVoteAverage(String voteAverage);

    void displayReleaseDate(String releaseDate);

    void displayOverview(String overview);

}
