package com.joseangelmaneiro.movies.presentation;


public interface DetailMovieView extends BaseView {

    void displayImage(String url);

    void displayTitle(String title);

    void displayVoteAverage(String voteAverage);

    void displayReleaseDate(String releaseDate);

    void displayOverview(String overview);

    void goToBack();

}
