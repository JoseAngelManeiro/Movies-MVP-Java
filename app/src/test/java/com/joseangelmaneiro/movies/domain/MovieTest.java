package com.joseangelmaneiro.movies.domain;

import com.joseangelmaneiro.movies.utils.TestUtils;
import org.junit.Test;
import static junit.framework.Assert.assertEquals;
import static org.junit.Assert.*;


public class MovieTest {

    @Test
    public void compareTwoIdenticalMoviesReturnsEquals() throws Exception {
        Movie movieOne = TestUtils.createMainMovie();
        Movie movieTwo = TestUtils.createMainMovie();

        assertEquals(movieOne, movieTwo);
    }

    @Test
    public void compareTwoDifferentMoviesReturnsNotEquals() throws Exception {
        Movie movieOne = TestUtils.createMainMovie();
        Movie movieTwo = TestUtils.createAltMovie();

        assertNotEquals(movieOne, movieTwo);
    }

}