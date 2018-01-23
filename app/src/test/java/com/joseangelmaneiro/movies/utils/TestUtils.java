package com.joseangelmaneiro.movies.utils;

import com.joseangelmaneiro.movies.data.entity.MovieEntity;
import com.joseangelmaneiro.movies.data.entity.PageEntity;
import com.joseangelmaneiro.movies.domain.Movie;
import java.util.ArrayList;
import java.util.List;


public class TestUtils implements TestData{

    public static int DEFAULT_NUM_ITEMS = 10;


    private TestUtils(){}

    public static PageEntity createMainPage(){
        return new PageEntity(
                MAIN_NUM_PAGE,
                MAIN_TOTAL_RESULTS,
                MAIN_TOTAL_PAGES,
                createMovieEntityList(MAIN_NUM_MOVIES));
    }

    public static PageEntity createAltPage(){
        return new PageEntity(
                ALT_NUM_PAGE,
                ALT_TOTAL_RESULTS,
                ALT_TOTAL_PAGES,
                createMovieEntityList(ALT_NUM_MOVIES));
    }

    public static MovieEntity createMainMovieEntity(){
        return new MovieEntity(VOTE_COUNT, MAIN_MOVIE_ID, MOVIE_VIDEO, MAIN_VOTE_AVERAGE, MAIN_MOVIE_TITLE,
                MOVIE_POPULARITY, POSTER_PATH, MAIN_ORIGINAL_LANGUAGE, MAIN_ORIGINAL_TITLE,
                GENRE_IDS, BACKDROPPATH, ADULT, OVERVIEW, MAIN_RELEASE_DATE);
    }

    public static MovieEntity createAltMovieEntity(){
        return new MovieEntity(VOTE_COUNT, ALT_MOVIE_ID, MOVIE_VIDEO, ALT_VOTE_AVERAGE, ALT_MOVIE_TITLE,
                MOVIE_POPULARITY, POSTER_PATH, ALT_ORIGINAL_LANGUAGE, ALT_ORIGINAL_TITLE,
                GENRE_IDS, BACKDROPPATH, ADULT, OVERVIEW, ALT_RELEASE_DATE);
    }

    public static List<MovieEntity> createMovieEntityList(int numMovies){
        List<MovieEntity> movies = new ArrayList<>();
        for(int i=0; i<numMovies; i++){
            MovieEntity movie = new MovieEntity(VOTE_COUNT, i, MOVIE_VIDEO, MAIN_VOTE_AVERAGE, MAIN_MOVIE_TITLE,
                    MOVIE_POPULARITY, POSTER_PATH, MAIN_ORIGINAL_LANGUAGE, MAIN_ORIGINAL_TITLE,
                    GENRE_IDS, BACKDROPPATH, ADULT, OVERVIEW, MAIN_RELEASE_DATE);
            movies.add(movie);
        }
        return movies;
    }

    public static List<MovieEntity> createMainMovieEntityList(){
        return createMovieEntityList(DEFAULT_NUM_ITEMS);
    }

    public static Movie createMainMovie(){
        return new Movie(MAIN_MOVIE_ID, MAIN_VOTE_AVERAGE, MAIN_MOVIE_TITLE, POSTER_PATH,
                BACKDROPPATH, OVERVIEW, MAIN_RELEASE_DATE);
    }

    public static Movie createAltMovie(){
        return new Movie(ALT_MOVIE_ID, ALT_VOTE_AVERAGE, ALT_MOVIE_TITLE, POSTER_PATH,
                BACKDROPPATH, OVERVIEW, ALT_RELEASE_DATE);
    }

    public static List<Movie> createMovieList(int numMovies){
        List<Movie> movies = new ArrayList<>();
        for(int i=0; i<numMovies; i++){
            Movie movie = new Movie(i, MAIN_VOTE_AVERAGE, MAIN_MOVIE_TITLE, POSTER_PATH,
                    BACKDROPPATH, OVERVIEW, MAIN_RELEASE_DATE);
            movies.add(movie);
        }
        return movies;
    }

    public static List<Movie> createMainMovieList(){
        return createMovieList(DEFAULT_NUM_ITEMS);
    }

}
