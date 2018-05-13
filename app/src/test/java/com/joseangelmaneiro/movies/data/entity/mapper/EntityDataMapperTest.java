package com.joseangelmaneiro.movies.data.entity.mapper;

import com.joseangelmaneiro.movies.domain.Movie;
import com.joseangelmaneiro.movies.utils.TestUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import java.util.List;
import static org.junit.Assert.*;


public class EntityDataMapperTest {

    EntityDataMapper sut;

    @Before
    public void setUp() throws Exception {
        sut = new EntityDataMapper();
    }

    @After
    public void tearDown() throws Exception {
        sut = null;
    }

    @Test
    public void transform_ReturnsMovie(){
        Movie movieExpected = TestUtils.createMainMovie();

        assertEquals(movieExpected, sut.transform(TestUtils.createMainMovieEntity()));
    }

    @Test
    public void transform_ReturnsMovieList(){
        List<Movie> movieListExpected = TestUtils.createMainMovieList();

        assertEquals(movieListExpected, sut.transform(TestUtils.createMainMovieEntityList()));
    }

}