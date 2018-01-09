package com.joseangelmaneiro.movies.data.source.local.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import com.joseangelmaneiro.movies.data.Movie;
import java.util.ArrayList;
import java.util.List;


public class MoviesDatabaseHelper extends SQLiteOpenHelper {

    private static MoviesDatabaseHelper INSTANCE;

    //Singleton pattern
    public static synchronized MoviesDatabaseHelper getInstance(Context context){
        if(INSTANCE == null){
            INSTANCE = new MoviesDatabaseHelper(context.getApplicationContext());
        }
        return INSTANCE;
    }

    // Database Info
    private static final String DATABASE_NAME = "moviesDB";
    private static final int DATABASE_VERSION = 1;

    // Table Name
    private static final String TABLE_MOVIE = "movie";

    // Movie Table Columns
    private static final String KEY_ID = "id";
    private static final String KEY_VOTE_COUNT = "voteCount";
    private static final String KEY_VIDEO = "video";
    private static final String KEY_VOTE_AVERAGE = "voteAverage";
    private static final String KEY_TITLE = "title";
    private static final String KEY_POPULARITY = "popularity";
    private static final String KEY_POSTERPATH = "posterpath";
    private static final String KEY_ORIGINAL_LANGUAGE = "originalLanguage";
    private static final String KEY_ORIGINAL_TITLE = "originalTitle";
    private static final String KEY_GENRE_IDS = "genreIds";
    private static final String KEY_BACKDROPPATH = "backdroppath";
    private static final String KEY_ADULT = "adult";
    private static final String KEY_OVERVIEW = "overview";
    private static final String KEY_RELEASEDATE = "releasedate";


    //Private to prevent direct instantiation
    private MoviesDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Called when the database is created for the FIRST time.
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_MOVIE_TABLE = "CREATE TABLE " + TABLE_MOVIE +
                "(" +
                KEY_ID + " INTEGER PRIMARY KEY," +
                KEY_VOTE_COUNT + " INTEGER," +
                KEY_VIDEO + " INTEGER," +
                KEY_VOTE_AVERAGE + " TEXT," +
                KEY_TITLE + " TEXT," +
                KEY_POPULARITY + " REAL," +
                KEY_POSTERPATH + " TEXT," +
                KEY_ORIGINAL_LANGUAGE + " TEXT," +
                KEY_ORIGINAL_TITLE + " TEXT," +
                KEY_GENRE_IDS + " TEXT," +
                KEY_BACKDROPPATH + " TEXT," +
                KEY_ADULT + " INTEGER, " +
                KEY_OVERVIEW + " TEXT," +
                KEY_RELEASEDATE + " TEXT" +
                ")";

        db.execSQL(CREATE_MOVIE_TABLE);
    }

    // Called when the database needs to be upgraded.
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Simplest implementation is to drop all old tables and recreate them
        if (oldVersion != newVersion) {
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_MOVIE);
            onCreate(db);
        }
    }

    public void addMovies(List<Movie> movieList) {
        SQLiteDatabase db = getWritableDatabase();

        // It's a good idea to wrap our insert in a transaction.
        // This helps with performance and ensures consistency of the database.
        db.beginTransaction();
        try {
            for(Movie movie : movieList){
                ContentValues values = new ContentValues();
                values.put(KEY_ID, movie.getId());
                values.put(KEY_VOTE_COUNT, movie.getVoteCount());
                values.put(KEY_VIDEO, movie.isVideo() ? 1 : 0);
                values.put(KEY_VOTE_AVERAGE, movie.getVoteAverage());
                values.put(KEY_TITLE, movie.getTitle());
                values.put(KEY_POPULARITY, movie.getPopularity());
                values.put(KEY_POSTERPATH, movie.getPosterPath());
                values.put(KEY_ORIGINAL_LANGUAGE, movie.getOriginalLanguage());
                values.put(KEY_ORIGINAL_TITLE, movie.getOriginalTitle());
                values.put(KEY_GENRE_IDS, DBUtils.transformIntegerListToString(movie.getGenreIds()));
                values.put(KEY_BACKDROPPATH, movie.getBackdropPath());
                values.put(KEY_ADULT, movie.isAdult() ? 1 : 0);
                values.put(KEY_OVERVIEW, movie.getOverview());
                values.put(KEY_RELEASEDATE, movie.getReleaseDate());

                db.insertOrThrow(TABLE_MOVIE, null, values);
            }
            db.setTransactionSuccessful();
        } catch (Exception e) {
            Log.d("MoviesDB", "Error while trying to add movie to database");
        } finally {
            db.endTransaction();
        }
    }

    public List<Movie> getAllMovies() {
        List<Movie> movieList = new ArrayList<>();

        String MOVIES_SELECT_QUERY = "SELECT * FROM " + TABLE_MOVIE;

        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery(MOVIES_SELECT_QUERY, null);
        try {
            if (cursor.moveToFirst()) {
                do {
                    movieList.add(createMovie(cursor));
                } while(cursor.moveToNext());
            }
        } catch (Exception e) {
            Log.d("MoviesDB", "Error while trying to get movies from database");
        } finally {
            if (cursor != null && !cursor.isClosed()) {
                cursor.close();
            }
        }
        return movieList;
    }

    public Movie getMovie(int id){
        Movie movie = null;

        String MOVIE_SELECT_QUERY = "SELECT * FROM " + TABLE_MOVIE + " WHERE " + KEY_ID + " = " + id;

        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery(MOVIE_SELECT_QUERY, null);
        try {
            if (cursor.moveToFirst()) {
                do {
                    movie = createMovie(cursor);
                } while(cursor.moveToNext());
            }
        } catch (Exception e) {
            Log.d("MoviesDB", "Error while trying to get movie from database");
        } finally {
            if (cursor != null && !cursor.isClosed()) {
                cursor.close();
            }
        }
        return movie;
    }

    public void deleteAllMovies() {
        SQLiteDatabase db = getWritableDatabase();
        try {
            db.delete(TABLE_MOVIE, null, null);
        } catch (Exception e) {
            Log.d("MoviesDB", "Error while trying to delete all movies");
        }
    }

    private Movie createMovie(Cursor cursor){
        Movie movie = new Movie();
        movie.setId(cursor.getInt(cursor.getColumnIndex(KEY_ID)));
        movie.setVoteCount(cursor.getInt(cursor.getColumnIndex(KEY_VOTE_COUNT)));
        movie.setVideo(cursor.getInt(cursor.getColumnIndex(KEY_VIDEO)) == 1);
        movie.setVoteAverage(cursor.getString(cursor.getColumnIndex(KEY_VOTE_AVERAGE)));
        movie.setTitle(cursor.getString(cursor.getColumnIndex(KEY_TITLE)));
        movie.setPopularity(cursor.getFloat(cursor.getColumnIndex(KEY_POPULARITY)));
        movie.setPosterPath(cursor.getString(cursor.getColumnIndex(KEY_POSTERPATH)));
        movie.setOriginalLanguage(cursor.getString(cursor.getColumnIndex(KEY_ORIGINAL_LANGUAGE)));
        movie.setOriginalTitle(cursor.getString(cursor.getColumnIndex(KEY_ORIGINAL_TITLE)));
        movie.setGenreIds(DBUtils.transformStringToIntegerList(
                cursor.getString(cursor.getColumnIndex(KEY_GENRE_IDS))));
        movie.setBackdropPath(cursor.getString(cursor.getColumnIndex(KEY_BACKDROPPATH)));
        movie.setAdult(cursor.getInt(cursor.getColumnIndex(KEY_ADULT)) == 1);
        movie.setOverview(cursor.getString(cursor.getColumnIndex(KEY_OVERVIEW)));
        movie.setReleaseDate(cursor.getString(cursor.getColumnIndex(KEY_RELEASEDATE)));
        return movie;
    }

}
