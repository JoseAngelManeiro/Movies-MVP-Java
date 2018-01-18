package com.joseangelmaneiro.movies.data.entity;

import java.util.List;
import java.util.Objects;

import com.google.gson.annotations.SerializedName;


public class PageEntity {

    @SerializedName("page")
    private int page;
    @SerializedName("total_results")
    private int totalResults;
    @SerializedName("total_pages")
    private int totalPages;
    @SerializedName("results")
    private List<MovieEntity> movies;

    /**
     * No args constructor for use in serialization
     *
     */
    public PageEntity() {
    }

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

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getTotalResults() {
        return totalResults;
    }

    public void setTotalResults(int totalResults) {
        this.totalResults = totalResults;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public List<MovieEntity> getMovies() {
        return movies;
    }

    public void setMovies(List<MovieEntity> movies) {
        this.movies = movies;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PageEntity that = (PageEntity) o;

        return Objects.equals(page, that.page) &&
                Objects.equals(totalResults, that.totalResults) &&
                Objects.equals(totalPages, that.totalPages) &&
                Objects.equals(movies, that.movies);
    }

    @Override
    public int hashCode() {
        return Objects.hash(page, totalResults, totalPages, movies);
    }

}
