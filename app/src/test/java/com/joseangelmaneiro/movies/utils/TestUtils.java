package com.joseangelmaneiro.movies.utils;

import com.joseangelmaneiro.movies.data.Movie;
import com.joseangelmaneiro.movies.ui.MovieViewModel;
import java.util.ArrayList;
import java.util.List;


public class TestUtils implements TestData{

    private TestUtils(){}

    public static Movie createMainMovie(){
        return new Movie(VOTE_COUNT, MAIN_MOVIE_ID, MOVIE_VIDEO, MAIN_VOTE_AVERAGE, MAIN_MOVIE_TITLE,
                MOVIE_POPULARITY, POSTER_PATH, MAIN_ORIGINAL_LANGUAGE, MAIN_ORIGINAL_TITLE,
                GENRE_IDS, BACKDROPPATH, ADULT, OVERVIEW, MAIN_RELEASE_DATE);
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

    public static List<MovieViewModel> createMainMovieViewModelList(int numMovies){
        List<MovieViewModel> movies = new ArrayList<>();
        for(int i=0; i<numMovies; i++){
            MovieViewModel movieViewModel = new MovieViewModel(i, MAIN_MOVIE_VM_VOTE_AVERAGE,
                    MAIN_MOVIE_VM_TITLE, MAIN_MOVIE_VM_POSTER_PATH, MAIN_MOVIE_VM_BACKDROPPATH,
                    MAIN_MOVIE_VM_OVERVIEW, MAIN_MOVIE_VM_RELEASE_DATE);
            movies.add(movieViewModel);
        }
        return movies;
    }

    public static List<MovieViewModel> createMainMovieViewModelList(){
        return createMainMovieViewModelList(10);
    }

}
