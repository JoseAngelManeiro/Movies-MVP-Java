package com.joseangelmaneiro.movies.data.source.local;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.text.TextUtils;
import com.joseangelmaneiro.movies.data.entity.MovieEntity;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class MoviesDatabaseHelper extends SQLiteOpenHelper {

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

    private static final String SEPARATOR = ",";

    @Inject
    public MoviesDatabaseHelper(Context context) {
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

    public void deleteAll() {
        SQLiteDatabase db = getWritableDatabase();
        db.delete(TABLE_MOVIE, null, null);
    }

    public void save(List<MovieEntity> movieEntityList) {
        SQLiteDatabase db = getWritableDatabase();

        // It's a good idea to wrap our insert in a transaction.
        // This helps with performance and ensures consistency of the database.
        db.beginTransaction();
        for(MovieEntity movieEntity : movieEntityList){
            db.insert(TABLE_MOVIE, null, createContentValues(movieEntity));
        }
        db.setTransactionSuccessful();
        db.endTransaction();
    }

    private ContentValues createContentValues(MovieEntity movieEntity){
        ContentValues values = new ContentValues();
        values.put(KEY_ID, movieEntity.id);
        values.put(KEY_VOTE_COUNT, movieEntity.voteCount);
        values.put(KEY_VIDEO, movieEntity.video ? 1 : 0);
        values.put(KEY_VOTE_AVERAGE, movieEntity.voteAverage);
        values.put(KEY_TITLE, movieEntity.title);
        values.put(KEY_POPULARITY, movieEntity.popularity);
        values.put(KEY_POSTERPATH, movieEntity.posterPath);
        values.put(KEY_ORIGINAL_LANGUAGE, movieEntity.originalLanguage);
        values.put(KEY_ORIGINAL_TITLE, movieEntity.originalTitle);
        values.put(KEY_GENRE_IDS, transformIntegerListToString(movieEntity.genreIds));
        values.put(KEY_BACKDROPPATH, movieEntity.backdropPath);
        values.put(KEY_ADULT, movieEntity.adult ? 1 : 0);
        values.put(KEY_OVERVIEW, movieEntity.overview);
        values.put(KEY_RELEASEDATE, movieEntity.releaseDate);
        return values;
    }

    public MovieEntity get(int id){
        SQLiteDatabase db = getReadableDatabase();
        String sql = "SELECT * FROM " + TABLE_MOVIE + " WHERE " + KEY_ID + " = " + id;
        MovieEntity movieEntity = new MovieEntity();

        Cursor cursor = db.rawQuery(sql, null);
        if (cursor.moveToFirst()) {
            do {
                movieEntity = createMovieEntity(cursor);
            } while(cursor.moveToNext());
        }

        if (!cursor.isClosed()) {
            cursor.close();
        }

        return movieEntity;
    }

    private MovieEntity createMovieEntity(Cursor cursor){
        MovieEntity movie = new MovieEntity();
        movie.id = cursor.getInt(cursor.getColumnIndex(KEY_ID));
        movie.voteCount = cursor.getInt(cursor.getColumnIndex(KEY_VOTE_COUNT));
        movie.video = cursor.getInt(cursor.getColumnIndex(KEY_VIDEO)) == 1;
        movie.voteAverage = cursor.getString(cursor.getColumnIndex(KEY_VOTE_AVERAGE));
        movie.title = cursor.getString(cursor.getColumnIndex(KEY_TITLE));
        movie.popularity = cursor.getFloat(cursor.getColumnIndex(KEY_POPULARITY));
        movie.posterPath = cursor.getString(cursor.getColumnIndex(KEY_POSTERPATH));
        movie.originalLanguage = cursor.getString(cursor.getColumnIndex(KEY_ORIGINAL_LANGUAGE));
        movie.originalTitle = cursor.getString(cursor.getColumnIndex(KEY_ORIGINAL_TITLE));
        movie.genreIds = transformStringToIntegerList(
                cursor.getString(cursor.getColumnIndex(KEY_GENRE_IDS)));
        movie.backdropPath = cursor.getString(cursor.getColumnIndex(KEY_BACKDROPPATH));
        movie.adult = cursor.getInt(cursor.getColumnIndex(KEY_ADULT)) == 1;
        movie.overview = cursor.getString(cursor.getColumnIndex(KEY_OVERVIEW));
        movie.releaseDate = cursor.getString(cursor.getColumnIndex(KEY_RELEASEDATE));
        return movie;
    }

    public List<MovieEntity> getAll() {
        SQLiteDatabase db = getReadableDatabase();
        String sql = "SELECT * FROM " + TABLE_MOVIE;
        List<MovieEntity> movieEntityList = new ArrayList<>();

        Cursor cursor = db.rawQuery(sql, null);
        if (cursor.moveToFirst()) {
            do {
                movieEntityList.add(createMovieEntity(cursor));
            } while(cursor.moveToNext());
        }

        if (!cursor.isClosed()) {
            cursor.close();
        }

        return movieEntityList;
    }

    private String transformIntegerListToString(List<Integer> integerList){
        return TextUtils.join(SEPARATOR, integerList);
    }

    private List<Integer> transformStringToIntegerList(String text){
        String[] arrayDB = text.split(SEPARATOR);
        List<Integer> integerList = new ArrayList<>();
        for(String s : arrayDB){
            integerList.add(Integer.parseInt(s));
        }
        return integerList;
    }

}
