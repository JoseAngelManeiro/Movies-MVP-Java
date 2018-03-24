package com.joseangelmaneiro.movies.data.entity.mapper;

import com.joseangelmaneiro.movies.data.entity.MovieEntity;
import com.joseangelmaneiro.movies.domain.Movie;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import javax.inject.Singleton;


// Mapper class used to transform MovieEntity, in the data layer, to Movie, in the domain layer.
@Singleton
public class EntityDataMapper {

    public static final String BASE_URL_IMAGE = "https://image.tmdb.org/t/p/w500";

    @Inject
    public EntityDataMapper(){}

    public List<Movie> transform(List<MovieEntity> movieEntityList){
        List<Movie> movieList = new ArrayList<>();
        for(MovieEntity movieEntity : movieEntityList){
            Movie movie = transform(movieEntity);
            if(movie != null){
                movieList.add(movie);
            }
        }
        return movieList;
    }

    public Movie transform(MovieEntity movieEntity){
        Movie movie = null;
        if(movieEntity != null){
            movie = new Movie(movieEntity.id,
                    movieEntity.voteAverage,
                    movieEntity.title,
                    completeUrl(movieEntity.posterPath),
                    completeUrl(movieEntity.backdropPath),
                    movieEntity.overview,
                    movieEntity.releaseDate);
        }
        return movie;
    }

    private String completeUrl(String path){
        return BASE_URL_IMAGE + path;
    }

}
