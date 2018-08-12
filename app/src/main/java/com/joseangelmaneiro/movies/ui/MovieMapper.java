package com.joseangelmaneiro.movies.ui;

import com.joseangelmaneiro.movies.data.Movie;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;


// Mapper class used to transform Movie, in the data layer, to MovieViewModel,
// in the presentation layer.
public class MovieMapper {

    public static final String BASE_URL_IMAGE = "https://image.tmdb.org/t/p/w500";

    private static final String DATA_DATE_FORMAT = "yyyy-MM-dd";
    private static final String MODEL_DATE_FORMAT = "dd/MM/yyyy";


    public MovieMapper(){}

    public List<MovieViewModel> transform(List<Movie> movieList){
        List<MovieViewModel> movieViewModelList = new ArrayList<>();
        for(Movie movie : movieList){
            MovieViewModel movieViewModel = transform(movie);
            if(movieViewModel!=null){
                movieViewModelList.add(movieViewModel);
            }
        }
        return movieViewModelList;
    }

    public MovieViewModel transform(Movie movie){
        if(movie!=null){
            return new MovieViewModel(movie.getId(),
                    movie.getVoteAverage(),
                    movie.getTitle(),
                    getCompleteUrlImage(movie.getPosterPath()),
                    getCompleteUrlImage(movie.getBackdropPath()),
                    movie.getOverview(),
                    formatDate(movie.getReleaseDate()));
        }
        return null;
    }

    private String getCompleteUrlImage(String posterPath){
        return BASE_URL_IMAGE + posterPath;
    }

    private String formatDate(String serverDate){
        SimpleDateFormat serverDateFormat = new SimpleDateFormat(DATA_DATE_FORMAT,
                Locale.getDefault());
        Date date = null;
        try {
            date = serverDateFormat.parse(serverDate);
        } catch (ParseException ignored) {}

        SimpleDateFormat appDateFormat = new SimpleDateFormat(MODEL_DATE_FORMAT,
                Locale.getDefault());
        return appDateFormat.format(date);
    }

}
