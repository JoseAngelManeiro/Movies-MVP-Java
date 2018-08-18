package com.joseangelmaneiro.movies.utils;

import java.util.List;


public interface TestData {

    int MAIN_MOVIE_ID = 1;
    int VOTE_COUNT = 5;
    boolean MOVIE_VIDEO = true;
    String MAIN_VOTE_AVERAGE = "6.2";
    String MAIN_MOVIE_TITLE = "Movie One";
    float MOVIE_POPULARITY = 5f;
    String POSTER_PATH = "/fake_poster_path.png";
    String MAIN_ORIGINAL_LANGUAGE = "ES";
    String MAIN_ORIGINAL_TITLE = "main_title";
    List<Integer> GENRE_IDS = null;
    String BACKDROPPATH = "/fake_backdroppath.png";
    boolean ADULT = false;
    String OVERVIEW = "Overview";
    String MAIN_RELEASE_DATE = "2017-10-22";

    int MAIN_MOVIE_VM_ID = 1;
    String MAIN_MOVIE_VM_VOTE_AVERAGE = "6.2";
    String MAIN_MOVIE_VM_TITLE = "Movie One";
    String MAIN_MOVIE_VM_POSTER_PATH = "https://image.tmdb.org/t/p/w500/fake_poster_path.png";
    String MAIN_MOVIE_VM_BACKDROPPATH = "https://image.tmdb.org/t/p/w500/fake_backdroppath.png";
    String MAIN_MOVIE_VM_OVERVIEW = "Overview";
    String MAIN_MOVIE_VM_RELEASE_DATE = "22/10/2017";

}
