package com.joseangelmaneiro.movies.data.source.local;


public final class Contract {

    private Contract(){}

    public static class Movie{
        static final String TABLE_NAME = "movie";
        public static final String COLUMN_ID = "id";
        public static final String COLUMN_VOTE_COUNT = "voteCount";
        public static final String COLUMN_VIDEO = "video";
        public static final String COLUMN_VOTE_AVERAGE = "voteAverage";
        public static final String COLUMN_TITLE = "title";
        public static final String COLUMN_POPULARITY = "popularity";
        public static final String COLUMN_POSTERPATH = "posterpath";
        public static final String COLUMN_ORIGINAL_LANGUAGE = "originalLanguage";
        public static final String COLUMN_ORIGINAL_TITLE = "originalTitle";
        public static final String COLUMN_GENRE_IDS = "genreIds";
        public static final String COLUMN_BACKDROPPATH = "backdroppath";
        public static final String COLUMN_ADULT = "adult";
        public static final String COLUMN_OVERVIEW = "overview";
        public static final String COLUMN_RELEASEDATE = "releasedate";
    }

}
