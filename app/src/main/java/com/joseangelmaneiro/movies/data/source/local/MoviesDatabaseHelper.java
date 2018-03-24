package com.joseangelmaneiro.movies.data.source.local;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
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
            db.insert(TABLE_NAME, null, movieEntity.getContentValues());
        }
        db.setTransactionSuccessful();
        db.endTransaction();
    }

    public MovieEntity get(int id){
        SQLiteDatabase db = getReadableDatabase();
        String sql = "SELECT * FROM " + TABLE_NAME + " WHERE " + COLUMN_ID + " = " + id;
        MovieEntity movieEntity = null;

        Cursor cursor = db.rawQuery(sql, null);
        if (cursor.moveToFirst()) {
            movieEntity = new MovieEntity(cursor);
        }

        if (!cursor.isClosed()) {
            cursor.close();
        }

        return movieEntity;
    }

    public List<MovieEntity> getAll() {
        SQLiteDatabase db = getReadableDatabase();
        String sql = "SELECT * FROM " + TABLE_NAME;
        List<MovieEntity> movieEntityList = new ArrayList<>();

        Cursor cursor = db.rawQuery(sql, null);
        if (cursor.moveToFirst()) {
            do {
                movieEntityList.add(new MovieEntity(cursor));
            } while(cursor.moveToNext());
        }

        if (!cursor.isClosed()) {
            cursor.close();
        }

        return movieEntityList;
    }

}
