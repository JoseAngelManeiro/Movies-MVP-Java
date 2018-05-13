package com.joseangelmaneiro.movies.data.source.local;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.joseangelmaneiro.movies.data.entity.MovieEntity;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import static com.joseangelmaneiro.movies.data.source.local.Contract.Movie.*;


public class MoviesLocalDataSourceImpl implements MoviesLocalDataSource {

    private SQLiteOpenHelper sqLiteOpenHelper;

    @Inject
    public MoviesLocalDataSourceImpl(SQLiteOpenHelper sqLiteOpenHelper) {
        this.sqLiteOpenHelper = sqLiteOpenHelper;
    }

    @Override
    public List<MovieEntity> getAll() {
        SQLiteDatabase db = sqLiteOpenHelper.getReadableDatabase();
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

    @Override
    public MovieEntity get(int id) {
        SQLiteDatabase db = sqLiteOpenHelper.getReadableDatabase();
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

    @Override
    public void save(List<MovieEntity> movieEntityList) {
        SQLiteDatabase db = sqLiteOpenHelper.getWritableDatabase();

        // It's a good idea to wrap our insert in a transaction.
        // This helps with performance and ensures consistency of the database.
        db.beginTransaction();
        for(MovieEntity movieEntity : movieEntityList){
            db.insert(TABLE_NAME, null, movieEntity.getContentValues());
        }
        db.setTransactionSuccessful();
        db.endTransaction();
    }

    @Override
    public void deleteAll() {
        SQLiteDatabase db = sqLiteOpenHelper.getWritableDatabase();
        db.delete(TABLE_NAME, null, null);
    }

}
