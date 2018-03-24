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
import static com.joseangelmaneiro.movies.data.source.local.Contract.Movie.*;

@Singleton
public class MoviesDatabaseHelper extends SQLiteOpenHelper {

    // Database Info
    private static final String DATABASE_NAME = "moviesDB";
    private static final int DATABASE_VERSION = 1;

    private static final String SEPARATOR = ",";

    @Inject
    public MoviesDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Called when the database is created for the FIRST time.
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_MOVIE_TABLE = "CREATE TABLE " + TABLE_NAME +
                "(" +
                COLUMN_ID + " INTEGER PRIMARY KEY," +
                COLUMN_VOTE_COUNT + " INTEGER," +
                COLUMN_VIDEO + " INTEGER," +
                COLUMN_VOTE_AVERAGE + " TEXT," +
                COLUMN_TITLE + " TEXT," +
                COLUMN_POPULARITY + " REAL," +
                COLUMN_POSTERPATH + " TEXT," +
                COLUMN_ORIGINAL_LANGUAGE + " TEXT," +
                COLUMN_ORIGINAL_TITLE + " TEXT," +
                COLUMN_GENRE_IDS + " TEXT," +
                COLUMN_BACKDROPPATH + " TEXT," +
                COLUMN_ADULT + " INTEGER, " +
                COLUMN_OVERVIEW + " TEXT," +
                COLUMN_RELEASEDATE + " TEXT" +
                ")";

        db.execSQL(CREATE_MOVIE_TABLE);
    }

    // Called when the database needs to be upgraded.
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Simplest implementation is to drop all old tables and recreate them
        if (oldVersion != newVersion) {
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
            onCreate(db);
        }
    }

    public void deleteAll() {
        SQLiteDatabase db = getWritableDatabase();
        db.delete(TABLE_NAME, null, null);
    }

    public void save(List<MovieEntity> movieEntityList) {
        SQLiteDatabase db = getWritableDatabase();

        // It's a good idea to wrap our insert in a transaction.
        // This helps with performance and ensures consistency of the database.
        db.beginTransaction();
        for(MovieEntity movieEntity : movieEntityList){
            db.insert(TABLE_NAME, null, createContentValues(movieEntity));
        }
        db.setTransactionSuccessful();
        db.endTransaction();
    }

    private ContentValues createContentValues(MovieEntity movieEntity){
        ContentValues values = new ContentValues();
        values.put(COLUMN_ID, movieEntity.id);
        values.put(COLUMN_VOTE_COUNT, movieEntity.voteCount);
        values.put(COLUMN_VIDEO, movieEntity.video ? 1 : 0);
        values.put(COLUMN_VOTE_AVERAGE, movieEntity.voteAverage);
        values.put(COLUMN_TITLE, movieEntity.title);
        values.put(COLUMN_POPULARITY, movieEntity.popularity);
        values.put(COLUMN_POSTERPATH, movieEntity.posterPath);
        values.put(COLUMN_ORIGINAL_LANGUAGE, movieEntity.originalLanguage);
        values.put(COLUMN_ORIGINAL_TITLE, movieEntity.originalTitle);
        values.put(COLUMN_GENRE_IDS, transformIntegerListToString(movieEntity.genreIds));
        values.put(COLUMN_BACKDROPPATH, movieEntity.backdropPath);
        values.put(COLUMN_ADULT, movieEntity.adult ? 1 : 0);
        values.put(COLUMN_OVERVIEW, movieEntity.overview);
        values.put(COLUMN_RELEASEDATE, movieEntity.releaseDate);
        return values;
    }

    public MovieEntity get(int id){
        SQLiteDatabase db = getReadableDatabase();
        String sql = "SELECT * FROM " + TABLE_NAME + " WHERE " + COLUMN_ID + " = " + id;
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
        movie.id = cursor.getInt(cursor.getColumnIndex(COLUMN_ID));
        movie.voteCount = cursor.getInt(cursor.getColumnIndex(COLUMN_VOTE_COUNT));
        movie.video = cursor.getInt(cursor.getColumnIndex(COLUMN_VIDEO)) == 1;
        movie.voteAverage = cursor.getString(cursor.getColumnIndex(COLUMN_VOTE_AVERAGE));
        movie.title = cursor.getString(cursor.getColumnIndex(COLUMN_TITLE));
        movie.popularity = cursor.getFloat(cursor.getColumnIndex(COLUMN_POPULARITY));
        movie.posterPath = cursor.getString(cursor.getColumnIndex(COLUMN_POSTERPATH));
        movie.originalLanguage = cursor.getString(cursor.getColumnIndex(COLUMN_ORIGINAL_LANGUAGE));
        movie.originalTitle = cursor.getString(cursor.getColumnIndex(COLUMN_ORIGINAL_TITLE));
        movie.genreIds = transformStringToIntegerList(
                cursor.getString(cursor.getColumnIndex(COLUMN_GENRE_IDS)));
        movie.backdropPath = cursor.getString(cursor.getColumnIndex(COLUMN_BACKDROPPATH));
        movie.adult = cursor.getInt(cursor.getColumnIndex(COLUMN_ADULT)) == 1;
        movie.overview = cursor.getString(cursor.getColumnIndex(COLUMN_OVERVIEW));
        movie.releaseDate = cursor.getString(cursor.getColumnIndex(COLUMN_RELEASEDATE));
        return movie;
    }

    public List<MovieEntity> getAll() {
        SQLiteDatabase db = getReadableDatabase();
        String sql = "SELECT * FROM " + TABLE_NAME;
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
