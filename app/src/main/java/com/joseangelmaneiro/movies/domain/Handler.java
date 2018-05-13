package com.joseangelmaneiro.movies.domain;

//Callback to comunicate between layers
public interface Handler<T> {

    void handle(T result);

    void error(Exception exception);

}
