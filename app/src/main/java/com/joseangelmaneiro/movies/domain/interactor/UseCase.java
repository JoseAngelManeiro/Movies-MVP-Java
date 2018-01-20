package com.joseangelmaneiro.movies.domain.interactor;

import com.joseangelmaneiro.movies.domain.Handler;


public interface UseCase<T, P> {

    void execute(Handler<T> handler, P params);

}
