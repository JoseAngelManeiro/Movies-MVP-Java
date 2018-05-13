package com.joseangelmaneiro.movies.data.exception;


public class ServiceException extends Exception {

    public ServiceException(){
        super("An error has occurred with the server");
    }

}
