package com.joseangelmaneiro.movies.presentation.formatters;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import javax.inject.Inject;
import javax.inject.Singleton;


@Singleton
public class Formatter {

    private static final String SERVER_DATE_FORMAT = "yyyy-MM-dd";
    private static final String APP_DATE_FORMAT = "dd/MM/yyyy";

    @Inject
    public Formatter(){}

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
