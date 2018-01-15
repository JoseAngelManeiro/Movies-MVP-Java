package com.joseangelmaneiro.movies.data.source.local;

import com.joseangelmaneiro.movies.domain.Handler;
import com.joseangelmaneiro.movies.data.Movie;
import com.joseangelmaneiro.movies.data.source.local.db.MoviesDatabaseHelper;
import java.util.List;


public class MoviesLocalDataSourceImpl implements MoviesLocalDataSource {

    private static MoviesLocalDataSourceImpl INSTANCE;

    private MoviesDatabaseHelper moviesDatabaseHelper;

    // Prevent direct instantiation.
    private MoviesLocalDataSourceImpl(MoviesDatabaseHelper moviesDatabaseHelper) {
        this.moviesDatabaseHelper = moviesDatabaseHelper;
    }

    public static MoviesLocalDataSourceImpl getInstance(MoviesDatabaseHelper moviesDatabaseHelper) {
        if (INSTANCE == null) {
            INSTANCE = new MoviesLocalDataSourceImpl(moviesDatabaseHelper);
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
