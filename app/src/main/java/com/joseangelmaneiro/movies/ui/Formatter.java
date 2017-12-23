package com.joseangelmaneiro.movies.ui;


public class Formatter {

    private static final String BASE_URL_IMAGE = "https://image.tmdb.org/t/p/w500";


    public Formatter(){}

    public String getCompleteUrlImage(String posterPath){
        return BASE_URL_IMAGE + posterPath;
    }

    public String getVoteAverage(double voteAverage){
        return Double.toString(voteAverage);
    }

}
