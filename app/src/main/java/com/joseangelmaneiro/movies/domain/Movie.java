package com.joseangelmaneiro.movies.domain;

import java.util.Objects;


// Movie in the domain layer
public final class Movie {

    private final int id;
    private final String voteAverage;
    private final String title;
    private final String posterPath;
    private final String backdropPath;
    private final String overview;
    private final String releaseDate;
    

    public Movie(int id, String voteAverage, String title, String posterPath,
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

        Movie that = (Movie) o;

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
