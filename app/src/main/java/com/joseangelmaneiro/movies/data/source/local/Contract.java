package com.joseangelmaneiro.movies.data.source.local;


public final class Contract {

    private Contract(){}

    public static class Movie{
        static final String TABLE_NAME = "movie";
        static final String COLUMN_ID = "id";
        static final String COLUMN_VOTE_COUNT = "voteCount";
        static final String COLUMN_VIDEO = "video";
        static final String COLUMN_VOTE_AVERAGE = "voteAverage";
        static final String COLUMN_TITLE = "title";
        static final String COLUMN_POPULARITY = "popularity";
        static final String COLUMN_POSTERPATH = "posterpath";
        static final String COLUMN_ORIGINAL_LANGUAGE = "originalLanguage";
        static final String COLUMN_ORIGINAL_TITLE = "originalTitle";
        static final String COLUMN_GENRE_IDS = "genreIds";
        static final String COLUMN_BACKDROPPATH = "backdroppath";
        static final String COLUMN_ADULT = "adult";
        static final String COLUMN_OVERVIEW = "overview";
        static final String COLUMN_RELEASEDATE = "releasedate";
    }

}
