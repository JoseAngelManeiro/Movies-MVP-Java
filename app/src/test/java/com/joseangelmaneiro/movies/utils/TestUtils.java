package com.joseangelmaneiro.movies.utils;


import com.joseangelmaneiro.movies.data.entity.MovieEntity;
import com.joseangelmaneiro.movies.data.entity.PageEntity;
import java.util.ArrayList;
import java.util.List;


public class TestUtils implements TestData{

    private TestUtils(){

    }

    public static PageEntity createMainPage(){
        return new PageEntity(
                MAIN_NUM_PAGE,
                MAIN_TOTAL_RESULTS,
                MAIN_TOTAL_PAGES,
                createMovieList(MAIN_NUM_MOVIES));
    }

    public static PageEntity createAltPage(){
        return new PageEntity(
                ALT_NUM_PAGE,
                ALT_TOTAL_RESULTS,
                ALT_TOTAL_PAGES,
                createMovieList(ALT_NUM_MOVIES));
    }

    public static MovieEntity createMainMovie(){
        return new MovieEntity(VOTE_COUNT, MAIN_MOVIE_ID, MOVIE_VIDEO, MAIN_VOTE_AVERAGE, MAIN_MOVIE_TITLE,
                MOVIE_POPULARITY, POSTER_PATH, MAIN_ORIGINAL_LANGUAGE, MAIN_ORIGINAL_TITLE,
                GENRE_IDS, BACKDROPPATH, ADULT, OVERVIEW, MAIN_RELEASE_DATE);
    }

    public static MovieEntity createAltMovie(){
        return new MovieEntity(VOTE_COUNT, ALT_MOVIE_ID, MOVIE_VIDEO, ALT_VOTE_AVERAGE, ALT_MOVIE_TITLE,
                MOVIE_POPULARITY, POSTER_PATH, ALT_ORIGINAL_LANGUAGE, ALT_ORIGINAL_TITLE,
                GENRE_IDS, BACKDROPPATH, ADULT, OVERVIEW, ALT_RELEASE_DATE);
    }

    public static List<MovieEntity> createMovieList(int numMovies){
        List<MovieEntity> movies = new ArrayList<>();
        for(int i=0; i<numMovies; i++){
            MovieEntity movie = new MovieEntity(VOTE_COUNT, i, MOVIE_VIDEO, MAIN_VOTE_AVERAGE, MAIN_MOVIE_TITLE,
                    MOVIE_POPULARITY, POSTER_PATH, MAIN_ORIGINAL_LANGUAGE, MAIN_ORIGINAL_TITLE,
                    GENRE_IDS, BACKDROPPATH, ADULT, OVERVIEW, MAIN_RELEASE_DATE);
            movies.add(movie);
        }
        return movies;
    }

    public static List<MovieEntity> createMainMovieList(){
        return createMovieList(10);
    }

}
