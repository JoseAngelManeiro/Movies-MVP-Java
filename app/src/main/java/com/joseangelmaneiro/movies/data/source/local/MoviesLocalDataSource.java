package com.joseangelmaneiro.movies.data.source.local;

import com.joseangelmaneiro.movies.data.Movie;
import com.joseangelmaneiro.movies.data.source.MoviesDataSource;
import com.joseangelmaneiro.movies.data.source.local.db.MoviesDatabaseHelper;
import java.util.List;


public class MoviesLocalDataSource implements MoviesDataSource {

    private static MoviesLocalDataSource INSTANCE;

    private MoviesDatabaseHelper moviesDatabaseHelper;

    // Prevent direct instantiation.
    private MoviesLocalDataSource(MoviesDatabaseHelper moviesDatabaseHelper) {
        this.moviesDatabaseHelper = moviesDatabaseHelper;
    }

    public static MoviesLocalDataSource getInstance(MoviesDatabaseHelper moviesDatabaseHelper) {
        if (INSTANCE == null) {
            INSTANCE = new MoviesLocalDataSource(moviesDatabaseHelper);
        }
        return INSTANCE;
    }

    @Override
    public void getMovies(Handler<List<Movie>> handler) {
        List<Movie> movieList = moviesDatabaseHelper.getAllMovies();
        if(movieList!=null && !movieList.isEmpty()){
            handler.handle(movieList);
        } else{
            handler.error();
        }
    }

    @Override
    public void getMovie(int movieId, Handler<Movie> handler) {
        Movie movie = moviesDatabaseHelper.getMovie(movieId);
        if(movie!=null){
            handler.handle(movie);
        } else{
            handler.error();
        }
    }

    @Override
    public void saveMovies(List<Movie> movieList) {
        moviesDatabaseHelper.addMovies(movieList);
    }

    @Override
    public void deleteAllMovies() {
        moviesDatabaseHelper.deleteAllMovies();
    }

}
