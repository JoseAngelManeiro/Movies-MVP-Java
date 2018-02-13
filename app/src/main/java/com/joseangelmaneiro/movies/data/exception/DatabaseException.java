package com.joseangelmaneiro.movies.data.exception;


public class DatabaseException extends Exception {

    private static final String DEFAULT_MESSAGE = "A database error has occurred";

    public DatabaseException(String errorMessage){
        super(errorMessage==null ? DEFAULT_MESSAGE : errorMessage);
    }

}
