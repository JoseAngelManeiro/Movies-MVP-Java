package com.joseangelmaneiro.movies.domain;

import java.util.List;


public interface MoviesRepository {

    List<Movie> getMovies(boolean onlyOnline) throws Exception;

    Movie getMovie(int movieId);

}
