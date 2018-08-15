package com.joseangelmaneiro.movies.utils;

import com.joseangelmaneiro.movies.data.Movie;
import com.joseangelmaneiro.movies.data.Page;
import com.joseangelmaneiro.movies.ui.MovieViewModel;
import java.util.ArrayList;
import java.util.List;


public class TestUtils implements TestData{

    private TestUtils(){}

    public static Page createMainPage(){
        return new Page(
                MAIN_NUM_PAGE,
                MAIN_TOTAL_RESULTS,
                MAIN_TOTAL_PAGES,
                createMovieList(MAIN_NUM_MOVIES));
    }

    public static Page createAltPage(){
        return new Page(
                ALT_NUM_PAGE,
                ALT_TOTAL_RESULTS,
                ALT_TOTAL_PAGES,
                createMovieList(ALT_NUM_MOVIES));
    }

    public static Movie createMainMovie(){
        return new Movie(VOTE_COUNT, MAIN_MOVIE_ID, MOVIE_VIDEO, MAIN_VOTE_AVERAGE, MAIN_MOVIE_TITLE,
                MOVIE_POPULARITY, POSTER_PATH, MAIN_ORIGINAL_LANGUAGE, MAIN_ORIGINAL_TITLE,
                GENRE_IDS, BACKDROPPATH, ADULT, OVERVIEW, MAIN_RELEASE_DATE);
    }

    public static Movie createAltMovie(){
        return new Movie(VOTE_COUNT, ALT_MOVIE_ID, MOVIE_VIDEO, ALT_VOTE_AVERAGE, ALT_MOVIE_TITLE,
                MOVIE_POPULARITY, POSTER_PATH, ALT_ORIGINAL_LANGUAGE, ALT_ORIGINAL_TITLE,
                GENRE_IDS, BACKDROPPATH, ADULT, OVERVIEW, ALT_RELEASE_DATE);
    }

    public static List<Movie> createMovieList(int numMovies){
        List<Movie> movies = new ArrayList<>();
        for(int i=0; i<numMovies; i++){
            Movie movie = new Movie(VOTE_COUNT, i, MOVIE_VIDEO, MAIN_VOTE_AVERAGE, MAIN_MOVIE_TITLE,
                    MOVIE_POPULARITY, POSTER_PATH, MAIN_ORIGINAL_LANGUAGE, MAIN_ORIGINAL_TITLE,
                    GENRE_IDS, BACKDROPPATH, ADULT, OVERVIEW, MAIN_RELEASE_DATE);
            movies.add(movie);
        }
        return movies;
    }

    public static List<Movie> createMainMovieList(){
        return createMovieList(10);
    }

    public static MovieViewModel createMainMovieViewModel(){
        return new MovieViewModel(MAIN_MOVIE_VM_ID, MAIN_MOVIE_VM_VOTE_AVERAGE, MAIN_MOVIE_VM_TITLE,
                MAIN_MOVIE_VM_POSTER_PATH, MAIN_MOVIE_VM_BACKDROPPATH, MAIN_MOVIE_VM_OVERVIEW,
                MAIN_MOVIE_VM_RELEASE_DATE);
    }

}
