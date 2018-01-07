package com.joseangelmaneiro.movies.ui.detail;


public interface DetailMovieView {

    void displayImage(String url);

    void displayTitle(String title);

    void displayVoteAverage(String voteAverage);

    void displayReleaseDate(String releaseDate);

    void displayOverview(String overview);

}
