package com.joseangelmaneiro.movies.ui;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;


public class Formatter {

    private static final String BASE_URL_IMAGE = "https://image.tmdb.org/t/p/w500";

    private static final String SERVER_DATE_FORMAT = "yyyy-MM-dd";
    private static final String APP_DATE_FORMAT = "dd/MM/yyyy";


    public Formatter(){}

    public String getCompleteUrlImage(String posterPath){
        return BASE_URL_IMAGE + posterPath;
    }

    public String formatDate(String serverDate){
        SimpleDateFormat serverDateFormat = new SimpleDateFormat(SERVER_DATE_FORMAT,
                Locale.getDefault());
        Date date = null;
        try {
            date = serverDateFormat.parse(serverDate);
        } catch (ParseException ignored) {}

        SimpleDateFormat appDateFormat = new SimpleDateFormat(APP_DATE_FORMAT,
                Locale.getDefault());
        return appDateFormat.format(date);
    }

}
