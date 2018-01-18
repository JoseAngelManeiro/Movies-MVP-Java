package com.joseangelmaneiro.movies.data;

import com.joseangelmaneiro.movies.data.entity.MovieEntity;
import com.joseangelmaneiro.movies.utils.TestUtils;
import org.junit.Test;
import static junit.framework.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;


public class MovieTest {


    @Test
    public void compareTwoIdenticalMoviesReturnsEquals() throws Exception {
        MovieEntity movieOne = TestUtils.createMainMovie();
        MovieEntity movieTwo = TestUtils.createMainMovie();

        assertEquals(movieOne, movieTwo);
    }

    @Test
    public void compareTwoDifferentMoviesReturnsNotEquals() throws Exception {
        MovieEntity movieOne = TestUtils.createMainMovie();
        MovieEntity movieTwo = TestUtils.createAltMovie();

        assertNotEquals(movieOne, movieTwo);
    }

}