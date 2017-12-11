package com.joseangelmaneiro.movies.data;

//Callback to comunicate presenter with repository
public interface Handler<T> {

    void handle(T result);

    void error();

}
