package com.joseangelmaneiro.movies.ui;

import java.util.Objects;


public class MovieViewModel {

    private int id;
    private String voteAverage;
    private String title;
    private String posterPath;
    private String backdropPath;
    private String overview;
    private String releaseDate;

    public MovieViewModel(int id, String voteAverage, String title, String posterPath,
                          String backdropPath, String overview, String releaseDate) {
        this.id = id;
        this.voteAverage = voteAverage;
        this.title = title;
        this.posterPath = posterPath;
        this.backdropPath = backdropPath;
        this.overview = overview;
        this.releaseDate = releaseDate;
    }

    public int getId() {
        return id;
    }

    public String getVoteAverage() {
        return voteAverage;
    }

    public String getTitle() {
        return title;
    }

    public String getPosterPath() {
        return posterPath;
    }

    public String getBackdropPath() {
        return backdropPath;
    }

    public String getOverview() {
        return overview;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MovieViewModel that = (MovieViewModel) o;

        return Objects.equals(id, that.id) &&
                Objects.equals(voteAverage, that.voteAverage) &&
                Objects.equals(title, that.title) &&
                Objects.equals(posterPath, that.posterPath) &&
                Objects.equals(backdropPath, that.backdropPath) &&
                Objects.equals(overview, that.overview) &&
                Objects.equals(releaseDate, that.releaseDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, voteAverage, title, posterPath, backdropPath, overview, releaseDate);
    }

}
