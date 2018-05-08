package com.joseangelmaneiro.movies;

import com.joseangelmaneiro.movies.domain.Movie;

public class TestUtils {

    public static Movie getMovie(int movieId){
        return new Movie(
                movieId,
                "8.7",
                "Avengers: Infinity War",
                "https://image.tmdb.org/t/p/w500/7WsyChQLEftFiDOVTGkv3hFpyyt.jpg",
                "https://image.tmdb.org/t/p/w500/bOGkgRGdhrBYJSLpXaxhXVstddV.jpg",
                "As the Avengers and their allies have continued to protect the world from " +
                        "threats too large for any one hero to handle, a new danger has emerged " +
                        "from the cosmic shadows: Thanos. A despot of intergalactic infamy, " +
                        "his goal is to collect all six Infinity Stones, artifacts " +
                        "of unimaginable power, and use them to inflict his twisted will on all " +
                        "of reality. Everything the Avengers have fought for has led up to this " +
                        "moment - the fate of Earth and existence itself has never been " +
                        "more uncertain.",
                "2018-04-25");
    }

}
