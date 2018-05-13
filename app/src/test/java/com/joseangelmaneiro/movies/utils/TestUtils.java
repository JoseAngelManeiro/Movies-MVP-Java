package com.joseangelmaneiro.movies.utils;

import com.joseangelmaneiro.movies.data.entity.MovieEntity;
import com.joseangelmaneiro.movies.data.entity.mapper.EntityDataMapper;
import com.joseangelmaneiro.movies.domain.Movie;
import java.util.ArrayList;
import java.util.List;



public class TestUtils implements TestData{

    public static int DEFAULT_NUM_ITEMS = 10;


    private TestUtils(){}


    public static MovieEntity createMainMovieEntity(){
        return new MovieEntity(VOTE_COUNT, MAIN_MOVIE_ID, MOVIE_VIDEO, MAIN_VOTE_AVERAGE, MAIN_MOVIE_TITLE,
                MOVIE_POPULARITY, POSTER_PATH, MAIN_ORIGINAL_LANGUAGE, MAIN_ORIGINAL_TITLE,
                GENRE_IDS, BACKDROPPATH, ADULT, OVERVIEW, MAIN_RELEASE_DATE);
    }

    public static List<MovieEntity> createMainMovieEntityList(){
        return createMovieEntityList(DEFAULT_NUM_ITEMS);
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

    public static Movie createMainMovie(){
        return new Movie(MAIN_MOVIE_ID, MAIN_VOTE_AVERAGE, MAIN_MOVIE_TITLE, completeUrl(POSTER_PATH),
                completeUrl(BACKDROPPATH), OVERVIEW, MAIN_RELEASE_DATE);
    }

    public static Movie createAltMovie(){
        return new Movie(ALT_MOVIE_ID, ALT_VOTE_AVERAGE, ALT_MOVIE_TITLE, completeUrl(POSTER_PATH),
                completeUrl(BACKDROPPATH), OVERVIEW, ALT_RELEASE_DATE);
    }

    public static List<Movie> createMainMovieList(){
        return createMovieList(DEFAULT_NUM_ITEMS);
    }

    public static List<Movie> createMovieList(int numMovies){
        List<Movie> movies = new ArrayList<>();
        for(int i=0; i<numMovies; i++){
            Movie movie = new Movie(i, MAIN_VOTE_AVERAGE, MAIN_MOVIE_TITLE, completeUrl(POSTER_PATH),
                    completeUrl(BACKDROPPATH), OVERVIEW, MAIN_RELEASE_DATE);
            movies.add(movie);
        }
        return movies;
    }

    private static String completeUrl(String path){
        return EntityDataMapper.BASE_URL_IMAGE + path;
    }

}
