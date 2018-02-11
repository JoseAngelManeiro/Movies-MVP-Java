package com.joseangelmaneiro.movies.data.entity;

import java.util.List;
import com.google.gson.annotations.SerializedName;


public class MovieEntity {

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
    public MovieEntity(int voteCount, int id, boolean video, String voteAverage, String title, float popularity, String posterPath, String originalLanguage, String originalTitle, List<Integer> genreIds, String backdropPath, boolean adult, String overview, String releaseDate) {
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

}
