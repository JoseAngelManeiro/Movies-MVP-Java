package com.joseangelmaneiro.movies.utils;


import java.util.List;


public interface TestData {

    int MAIN_NUM_PAGE = 1;
    int MAIN_TOTAL_PAGES = 2;
    int MAIN_TOTAL_RESULTS = 40;
    int MAIN_NUM_MOVIES = 20;

    int ALT_NUM_PAGE = 2;
    int ALT_TOTAL_PAGES = 2;
    int ALT_TOTAL_RESULTS = 40;
    int ALT_NUM_MOVIES = 20;

    int MAIN_MOVIE_ID = 1;
    int VOTE_COUNT = 5;
    boolean MOVIE_VIDEO = true;
    float MAIN_VOTE_AVERAGE = 6.2f;
    String MAIN_MOVIE_TITLE = "Movie One";
    float MOVIE_POPULARITY = 5f;
    String POSTER_PATH = "fake_poster_path.png";
    String MAIN_ORIGINAL_LANGUAGE = "ES";
    String MAIN_ORIGINAL_TITLE = "main_title";
    List<Integer> GENRE_IDS = null;
    String BACKDROPPATH = "fake_backdroppath.png";
    boolean ADULT = false;
    String OVERVIEW = "Overview";
    String MAIN_RELEASE_DATE = "05/12/2017";

    int ALT_MOVIE_ID = 2;
    String ALT_MOVIE_TITLE = "Movie Two";
    String ALT_ORIGINAL_LANGUAGE = "EN";
    String ALT_ORIGINAL_TITLE = "alt_title";
    String ALT_RELEASE_DATE = "06/12/2017";
    float ALT_VOTE_AVERAGE = 4.3f;

}
