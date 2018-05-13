package com.joseangelmaneiro.movies.data.entity;

import android.content.ContentValues;
import android.database.Cursor;
import android.text.TextUtils;
import java.util.ArrayList;
import java.util.List;
import com.google.gson.annotations.SerializedName;
import static com.joseangelmaneiro.movies.data.source.local.Contract.Movie.*;


public class MovieEntity {

    private static final String SEPARATOR = ",";

    @SerializedName("vote_count")
    public int voteCount;
    @SerializedName("id")
    public int id;
    @SerializedName("video")
    public boolean video;
    @SerializedName("vote_average")
    public String voteAverage;
    @SerializedName("title")
    public String title;
    @SerializedName("popularity")
    public float popularity;
    @SerializedName("poster_path")
    public String posterPath;
    @SerializedName("original_language")
    public String originalLanguage;
    @SerializedName("original_title")
    public String originalTitle;
    @SerializedName("genre_ids")
    public List<Integer> genreIds = null;
    @SerializedName("backdrop_path")
    public String backdropPath;
    @SerializedName("adult")
    public boolean adult;
    @SerializedName("overview")
    public String overview;
    @SerializedName("release_date")
    public String releaseDate;

    /**
     * No args constructor for use in serialization
     *
     */
    public MovieEntity() {}

    /**
     *
     * @param genreIds
     * @param id
     * @param title
     * @param releaseDate
     * @param overview
     * @param posterPath
     * @param originalTitle
     * @param voteAverage
     * @param originalLanguage
     * @param adult
     * @param backdropPath
     * @param voteCount
     * @param video
     * @param popularity
     */
    public MovieEntity(int voteCount, int id, boolean video, String voteAverage, String title,
                       float popularity, String posterPath, String originalLanguage,
                       String originalTitle, List<Integer> genreIds, String backdropPath,
                       boolean adult, String overview, String releaseDate) {
        super();
        this.voteCount = voteCount;
        this.id = id;
        this.video = video;
        this.voteAverage = voteAverage;
        this.title = title;
        this.popularity = popularity;
        this.posterPath = posterPath;
        this.originalLanguage = originalLanguage;
        this.originalTitle = originalTitle;
        this.genreIds = genreIds;
        this.backdropPath = backdropPath;
        this.adult = adult;
        this.overview = overview;
        this.releaseDate = releaseDate;
    }

    public MovieEntity(Cursor cursor){
        super();
        this.id = cursor.getInt(cursor.getColumnIndex(COLUMN_ID));
        this.voteCount = cursor.getInt(cursor.getColumnIndex(COLUMN_VOTE_COUNT));
        this.video = cursor.getInt(cursor.getColumnIndex(COLUMN_VIDEO)) == 1;
        this.voteAverage = cursor.getString(cursor.getColumnIndex(COLUMN_VOTE_AVERAGE));
        this.title = cursor.getString(cursor.getColumnIndex(COLUMN_TITLE));
        this.popularity = cursor.getFloat(cursor.getColumnIndex(COLUMN_POPULARITY));
        this.posterPath = cursor.getString(cursor.getColumnIndex(COLUMN_POSTERPATH));
        this.originalLanguage = cursor.getString(cursor.getColumnIndex(COLUMN_ORIGINAL_LANGUAGE));
        this.originalTitle = cursor.getString(cursor.getColumnIndex(COLUMN_ORIGINAL_TITLE));
        this.genreIds = transformStringToIntegerList(
                cursor.getString(cursor.getColumnIndex(COLUMN_GENRE_IDS)));
        this.backdropPath = cursor.getString(cursor.getColumnIndex(COLUMN_BACKDROPPATH));
        this.adult = cursor.getInt(cursor.getColumnIndex(COLUMN_ADULT)) == 1;
        this.overview = cursor.getString(cursor.getColumnIndex(COLUMN_OVERVIEW));
        this.releaseDate = cursor.getString(cursor.getColumnIndex(COLUMN_RELEASEDATE));
    }

    public ContentValues getContentValues(){
        ContentValues values = new ContentValues();
        values.put(COLUMN_ID, this.id);
        values.put(COLUMN_VOTE_COUNT, this.voteCount);
        values.put(COLUMN_VIDEO, this.video ? 1 : 0);
        values.put(COLUMN_VOTE_AVERAGE, this.voteAverage);
        values.put(COLUMN_TITLE, this.title);
        values.put(COLUMN_POPULARITY, this.popularity);
        values.put(COLUMN_POSTERPATH, this.posterPath);
        values.put(COLUMN_ORIGINAL_LANGUAGE, this.originalLanguage);
        values.put(COLUMN_ORIGINAL_TITLE, this.originalTitle);
        values.put(COLUMN_GENRE_IDS, transformIntegerListToString(this.genreIds));
        values.put(COLUMN_BACKDROPPATH, this.backdropPath);
        values.put(COLUMN_ADULT, this.adult ? 1 : 0);
        values.put(COLUMN_OVERVIEW, this.overview);
        values.put(COLUMN_RELEASEDATE, this.releaseDate);
        return values;
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
