package com.joseangelmaneiro.movies.ui;

import com.joseangelmaneiro.movies.utils.TestUtils;
import org.junit.Test;
import static org.junit.Assert.*;


public class MovieMapperTest {

    @Test
    public void mapperTransformFromMovieToMovieViewModelCorrectly(){
        MovieMapper mapper = new MovieMapper();
        MovieViewModel expected = TestUtils.createMainMovieViewModel();

        MovieViewModel result = mapper.transform(TestUtils.createMainMovie());

        assertEquals(expected, result);
    }

}