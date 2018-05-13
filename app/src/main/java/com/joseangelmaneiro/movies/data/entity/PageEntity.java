package com.joseangelmaneiro.movies.data.entity;

import java.util.List;
import com.google.gson.annotations.SerializedName;


public class PageEntity {

    @SerializedName("page")
    public int page;
    @SerializedName("total_results")
    public int totalResults;
    @SerializedName("total_pages")
    public int totalPages;
    @SerializedName("results")
    public List<MovieEntity> movies;

    /**
     * No args constructor for use in serialization
     *
     */
    public PageEntity() {}

    /**
     *
     * @param movies
     * @param totalResults
     * @param page
     * @param totalPages
     */
    public PageEntity(int page, int totalResults, int totalPages, List<MovieEntity> movies) {
        super();
        this.page = page;
        this.totalResults = totalResults;
        this.totalPages = totalPages;
        this.movies = movies;
    }

}
